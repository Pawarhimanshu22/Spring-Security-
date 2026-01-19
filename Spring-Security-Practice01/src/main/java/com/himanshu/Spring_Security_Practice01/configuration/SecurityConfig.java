package com.himanshu.Spring_Security_Practice01.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
    @Autowired
    DataSource dataSource; // Injecting the DataSource bean to be used for JDBC authentication
    // basically database ke connection object ko inject kar rahe hai

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    {
        httpSecurity.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admin/heyy").hasRole("ADMIN")
                        .requestMatchers("/seller/heyy").hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers("/heyy").permitAll()
                        .anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults()); // For Not form based authentication
        return httpSecurity.build();
    }

    @Bean
    // In-Memory UserDetailsService
    // UserDetailsService is an interface that provides user details for authentication and authorization.
    // UserDetails is an interface that represents a user in the Spring Security framework.
    public UserDetailsService userDetailsService()
    {
        UserDetails user1 = User.withUsername("user1")
//              .password("{noop}pass1")// {noop} is used to specify that no encoding is done on the password
                .password(passwordEncoder().encode("pass1"))
                .roles("USER") //ROLE_USER
                .build();

        UserDetails user2 = User.withUsername("user2")
//                .password("{noop}pass2")
                .password(passwordEncoder().encode("pass1"))
                .roles("SELLER") //ROLE_SELLER
                .build();

        UserDetails admin = User.withUsername("admin")
//              .password("{noop}admin")
                .password(passwordEncoder().encode("pass1"))
                .roles("ADMIN") //ROLE_ADMIN
                .build();

//        return new InMemoryUserDetailsManager(user1, user2, admin);
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user1);
        jdbcUserDetailsManager.createUser(user2);
        jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
