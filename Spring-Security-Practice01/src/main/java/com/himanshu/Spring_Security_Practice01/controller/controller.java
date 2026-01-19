package com.himanshu.Spring_Security_Practice01.controller;

import com.himanshu.Spring_Security_Practice01.helper.LogInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller
{
    @Autowired
    AuthenticationManager authenticationManager;


    @PreAuthorize("permitAll()")
    @GetMapping("/heyy")
    public ResponseEntity<String> sayHello()
    {
        return ResponseEntity.ok("Hello, World!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/heyy")
    public ResponseEntity<String> sayAdminHello()
    {
        return ResponseEntity.ok("Hi, Admin!");
    }

    @GetMapping("/seller/heyy")
    public ResponseEntity<String> saySellerHello()
    {
        return ResponseEntity.ok("Greetings, Seller!");
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LogInRequest logInRequest)
    {
        //Represents the token for an authentication request
        Authentication authentication;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInRequest.getUsername(),
                            logInRequest.getPassword()
            )

        }
        catch (AuthenticationException e)
        {
           e.printStackTrace();
        }
        return "Login Successful!";
    }
}
