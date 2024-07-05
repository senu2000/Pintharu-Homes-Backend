package com.pintharuHomes.Backend.controller;

import com.pintharuHomes.Backend.dto.TokenDto;
import com.pintharuHomes.Backend.dto.UserDto;
import com.pintharuHomes.Backend.entity.Role;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    //    Build get user by username
    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUserByUsername (@PathVariable("username") String username){
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    //    Build get user by ID
    @GetMapping("byId/{id}")
    public ResponseEntity<UserDto> getUserById (@PathVariable("id") Integer id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    //    Build getId user by token
    @GetMapping("token/{token}")
    public ResponseEntity<TokenDto> getUserIDByToken (@PathVariable("token") String token){
        TokenDto tokenDto = userService.getUserByToken(token);
        return ResponseEntity.ok(tokenDto);
    }

    //    Build get all users by role
    @GetMapping("allusers/{role}")
    public ResponseEntity<List<UserDto>> getAllUsersByRole (@PathVariable("role") String role){
        Role userRole = Role.valueOf(role.toUpperCase()); // Convert string to Role enum
        List<UserDto> allUsers = userService.getAllUsersByRole(userRole);
        return ResponseEntity.ok(allUsers);
    }

    //    Build get all users count by role
    @GetMapping("count/{role}")
    public ResponseEntity<Integer> getUserCount (@PathVariable("role") String role){
        Role userRole = Role.valueOf(role.toUpperCase());
        Integer userCount = userService.getUserCount(userRole);
        return ResponseEntity.ok(userCount);
    }

//    Build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser (@PathVariable("id") Integer id){
        String user = userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }
}
