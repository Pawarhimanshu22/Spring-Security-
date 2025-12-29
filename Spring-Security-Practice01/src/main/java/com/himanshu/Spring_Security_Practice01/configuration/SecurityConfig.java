package com.himanshu.Spring_Security_Practice01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        httpSecurity.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admin/heyy").hasRole("ADMIN")
                        .requestMatchers("/seller/heyy").hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers("/heyy").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user1 = User.withUsername("user1")
                .password("{noop}pass1")
                .roles("USER") //ROLE_USER
                .build();

        UserDetails user2 = User.withUsername("user2")
                .password("{noop}pass2")
                .roles("SELLER") //ROLE_SELLER
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}adminpass")
                .roles("ADMIN") //ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

}
