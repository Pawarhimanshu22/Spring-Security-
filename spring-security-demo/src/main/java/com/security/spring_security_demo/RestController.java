package com.security.spring_security_demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/greet")
    public String greeting(HttpServletRequest request) {
        return "Hello, World!" + request.getSession().getId();
    }
    @GetMapping("/about")
    public String about(HttpServletRequest request)
    {
        return "This is a Spring Security Demo Application."+ request.getSession().getId();
    }
}
