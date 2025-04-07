package com.example.automated.websocket;

import com.example.automated.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationWebSocket {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendToUser(String username, Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications/" + username, notification);
    }
}