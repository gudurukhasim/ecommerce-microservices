package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.dto.OrderEvent;
import com.ecommerce.notificationservice.entity.Notification;
import com.ecommerce.notificationservice.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendOrderConfirmedNotification(OrderEvent event) {
        String message = String.format(
            "Dear %s, your order %s for ₹%.2f has been CONFIRMED! Thank you for shopping with us.",
            event.getUserEmail(),
            event.getOrderNumber(),
            event.getTotalAmount()
        );

        log.info("📧 SENDING EMAIL TO: {}", event.getUserEmail());
        log.info("📧 SUBJECT: Order Confirmed - {}", event.getOrderNumber());
        log.info("📧 MESSAGE: {}", message);

        Notification notification = Notification.builder()
                .orderNumber(event.getOrderNumber())
                .userEmail(event.getUserEmail())
                .amount(event.getTotalAmount())
                .type("ORDER_CONFIRMED")
                .message(message)
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        log.info("✅ Notification saved to MongoDB for order: {}", event.getOrderNumber());
    }

    public void sendOrderFailedNotification(OrderEvent event) {
        String message = String.format(
            "Dear %s, unfortunately your order %s for ₹%.2f could not be processed. Please try again.",
            event.getUserEmail(),
            event.getOrderNumber(),
            event.getTotalAmount()
        );

        log.info("📧 SENDING EMAIL TO: {}", event.getUserEmail());
        log.info("📧 SUBJECT: Order Failed - {}", event.getOrderNumber());
        log.info("📧 MESSAGE: {}", message);

        Notification notification = Notification.builder()
                .orderNumber(event.getOrderNumber())
                .userEmail(event.getUserEmail())
                .amount(event.getTotalAmount())
                .type("ORDER_FAILED")
                .message(message)
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        log.info("✅ Notification saved to MongoDB for order: {}", event.getOrderNumber());
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByUser(String userEmail) {
        return notificationRepository.findByUserEmail(userEmail);
    }

    public List<Notification> getNotificationsByOrder(String orderNumber) {
        return notificationRepository.findByOrderNumber(orderNumber);
    }
}