package com.himanshu.springsecurity.config;

import com.himanshu.springsecurity.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfig
{
    @Autowired
    private CustomUserService customUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {

        System.out.println("Creating SecurityFilterChain Bean...............3");
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/contactUS", "/aboutUS", "/updates").permitAll()
                        .requestMatchers("/balance", "/admin/dashboard", "/user/dashboard", "/transfer").authenticated()
                        .anyRequest().authenticated()
                )
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/balance", false)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")
//                        .permitAll()
//                );
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
/**
:- PasswordEncoder is used to securely hash user passwords before storing them in the database
:-  and to verify the raw password entered during login against the stored hashed password.
:-  BCryptPasswordEncoder applies salting and adaptive hashing to protect against
:-  brute-force and rainbow table attacks, making it suitable for production-grade security.
 **/
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception
    {
        System.out.println("Creating AuthenticationManager Bean...............1");
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}
