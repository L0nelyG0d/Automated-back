package com.example.automated.controller;

import com.example.automated.service.UserService;
import com.example.automated.model.Login;
import com.example.automated.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login){
        User user = userService.authenticate(login.getUsername(), login.getUsername());

        if(user != null){
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
