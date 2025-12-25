package com.himanshu.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> testEndPoint()
    {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/admin/test")
    public ResponseEntity<String> testAdminEndPoint()
    {
        return ResponseEntity.ok("Hello Admin");
    }


    @GetMapping("/contactUS")
    public ResponseEntity<String> contactUs() {
        return ResponseEntity.ok("Phone: 123-456-7890, Email: hdfcbank@gmail.com");
    }

    @GetMapping("/aboutUS")
    public ResponseEntity<String> aboutUs() {
        return ResponseEntity.ok("We are HDFC Bank, your trusted financial partner.");
    }

    @GetMapping("/updates")
    public ResponseEntity<String> updates() {
        return ResponseEntity.ok("Welcome to HDFC Bank! \n Car loan Drop to 6.5% \n Home Loan Drop to 7.5%");
    }

    @GetMapping("/balance")
    public ResponseEntity<String> checkBalance() {
        return ResponseEntity.ok("Your balance is $10,000");
    }

    @GetMapping("/transfer")
    public ResponseEntity<String> transferFunds() {
        return ResponseEntity.ok("Funds transferred successfully");
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome to the Admin Dashboard");
    }

    @GetMapping("/user/dashboard")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> userDashboard() {
        return ResponseEntity.ok("Welcome to the User Dashboard");
    }
}