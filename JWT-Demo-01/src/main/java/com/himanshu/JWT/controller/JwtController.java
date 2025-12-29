package com.himanshu.JWT.controller;


import com.himanshu.JWT.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class JwtController
{


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestParam String username, @RequestParam String password)
    {
        if ("admin".equals(username) && "admin".equals(password))
        {
            return ResponseEntity.ok(jwtUtil.GENERATE_TOKEN(username));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    @GetMapping("/fund-transfer")
    public ResponseEntity<String> fundTransfer(@RequestHeader(value = "Authorization", required= false) String authorizationHeader)
    {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            String token = authorizationHeader.substring(7);
            if (jwtUtil.VALIDATE_TOKEN(token))
            {
                return ResponseEntity.ok("Fund transfer successful");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid or expired token");
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header missing or invalid");
        }

    }
}