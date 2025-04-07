package com.example.automated.service;

import com.example.automated.repository.NotificationRepository;
import com.example.automated.model.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
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
    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);

    }
}
