package com.himanshu.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/contactUS", "/aboutUS", "/updates").permitAll()
                        .requestMatchers("/balance", "/transfer").authenticated()
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
}
