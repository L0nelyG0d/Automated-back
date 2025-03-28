package com.example.automated.controller;

import com.example.automated.model.User;
import com.example.automated.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    ResponseEntity<?> userLogin(@RequestBody User loginUser){
        Optional<User> user = userService.getUserByUsername(loginUser.getUsername());

        if(user.isPresent() && user.get().getPassword().equals(loginUser.getPassword())){
            return ResponseEntity.ok(loginUser);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @GetMapping("/add/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);

        return user.map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser( user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User updateUser,
                                           @PathVariable Long id,
                                           @RequestParam String change){

        Optional<User> user = userService.updateUser(updateUser,id, change);

        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
