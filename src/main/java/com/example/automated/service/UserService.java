package com.example.automated.service;

import com.example.automated.model.User;
import com.example.automated.repository.UserRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> updateUser(User updatedUser, Long id, String change){
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            switch (change.toLowerCase()) {
                case "username" -> user.setUsername(updatedUser.getUsername());
                case "email" -> user.setEmail(updatedUser.getEmail());
                case "password" -> user.setPassword(updatedUser.getPassword());
                default -> throw new IllegalArgumentException("Invalid field to update: " + change);
            }

            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
