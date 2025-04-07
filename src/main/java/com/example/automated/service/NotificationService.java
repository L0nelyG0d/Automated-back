package com.example.automated.service;

import com.example.automated.repository.NotificationRepository;
import com.example.automated.model.Notification;

import com.example.automated.websocket.NotificationWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    NotificationService(NotificationRepository notificationRepository, NotificationWebSocket notificationWebSocket){
        this.notificationRepository = notificationRepository;
        this.notificationWebSocket = notificationWebSocket;
    }

    private final NotificationWebSocket notificationWebSocket;

    public Notification sendNotification(Notification notification) {
        Notification saved = notificationRepository.save(notification);
        notificationWebSocket.sendToUser(notification.getUser().getUsername(), saved);
        return saved;
    }

    public List<Notification> getUnreadNotification(Long userId){
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    public void markNotificationsAsRead(Long notificationId){
       Notification notification = notificationRepository.findById(notificationId).
                orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);

        notificationRepository.save(notification);
    }

}
