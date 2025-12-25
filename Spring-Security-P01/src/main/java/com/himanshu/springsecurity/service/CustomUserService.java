package com.himanshu.springsecurity.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
/**
:- UserDetailsService interface is used to retrieve user-related data. It has a single method named
:- loadUserByUsername which can be overridden to customize the process of finding the user.
:- Basically Responsible for Loading the user data from Database
**/
 public class CustomUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.out.println("Custom User Details Service Bean Injected is....................2 : ");
        System.out.println("Saving User Data by   LoadUserByUsername");


        // In real application, you would fetch user details from a database
        // Here, we are hardcoding a single user for demonstration purposes
        //this is only for loading single user
//        if (username.equals("himanshu"))
//        {
//            String encodedPassword = new BCryptPasswordEncoder().encode("rootpass");
//            return   User
//                    .withUsername("himanshu")
//                    .password(encodedPassword)
//                    .roles("USER")
//                    .build();
//        }
//        else
//        {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }


        //This is For Loading Multiple Users
        if (username.equals("himanshu"))
        {
            String encodedPassword = new BCryptPasswordEncoder().encode("rootpass");
            return User
                    .withUsername("himanshu")
                    .password(encodedPassword)
                    .roles("ADMIN")
                    .build();
        }
        if (username.equals("karan"))
        {
            String encodedPasswordKaran = new BCryptPasswordEncoder().encode("pass");
            return User
                    .withUsername("karan")
                    .password(encodedPasswordKaran)
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
