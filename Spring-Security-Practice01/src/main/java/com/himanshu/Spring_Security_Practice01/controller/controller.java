package com.himanshu.Spring_Security_Practice01.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller
{
    @GetMapping("/heyy")
    public ResponseEntity<String> sayHello()
    {
        return ResponseEntity.ok("Hello, World!");
    }

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
}
