package com.example.automated.controller;

import com.example.automated.model.User;
import com.example.automated.repository.UserRepository;
import com.example.automated.service.UserService;
import com.example.automated.model.Notification;
import com.example.automated.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.automated.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final UserRepository userRepository;

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService,  UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getUnreadNotification(userId);
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/add")
    public ResponseEntity<?> sendNotification(@RequestBody Notification notification) {
        if (notification.getUser() == null || notification.getUser().getUsername() == null) {
            return ResponseEntity.badRequest().body("User is missing in request");
        }

        String username = notification.getUser().getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        notification.setUser(user);
        return ResponseEntity.ok(notificationService.sendNotification(notification));
    }


    @PutMapping("/mark-as-read/{id}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markNotificationsAsRead(id);
        return ResponseEntity.ok().build();
    }
}
