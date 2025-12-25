package com.himanshu.SpringSecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> home()
    {
        return ResponseEntity.ok("Welcome to the Home Page");
    }

    @GetMapping("/user")
  public ResponseEntity<String> user()
  {
      return ResponseEntity.ok("Welcome to the User Page");
  }


  @GetMapping("/admin")
  //@PreAuthorize("hasRole('User')")//ADMIN_
  public ResponseEntity<String> admin()
  {
      return ResponseEntity.ok("Welcome to the Admin Page");
  }
}
