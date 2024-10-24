package com.pintharuHomes.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user_only")
    public ResponseEntity<String> userOnly(){
        return ResponseEntity.ok("hello from secured URL");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly(){
        return ResponseEntity.ok("hello from admin only URL");
    }

}
