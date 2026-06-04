package com.ecommerce.notificationservice.controller;

import com.ecommerce.notificationservice.entity.Notification;
import com.ecommerce.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Notification>> getByUser(@PathVariable String email) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(email));
    }

    @GetMapping("/order/{orderNumber}")
    public ResponseEntity<List<Notification>> getByOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(notificationService.getNotificationsByOrder(orderNumber));
    }
}