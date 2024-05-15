package com.pintharuHomes.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/user_only")
    public ResponseEntity<String> userOnly(){
        return ResponseEntity.ok("hello from secured URL");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly(){
        return ResponseEntity.ok("hello from admin only URL");
    }

}
