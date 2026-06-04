package com.ecommerce.notificationservice.kafka;

import com.ecommerce.notificationservice.dto.OrderEvent;
import com.ecommerce.notificationservice.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "payment-success", groupId = "notification-service-group")
    public void handlePaymentSuccess(OrderEvent event) {
        log.info("🔔 Received PAYMENT_SUCCESS for order: {}", event.getOrderNumber());
        notificationService.sendOrderConfirmedNotification(event);
    }

    @KafkaListener(topics = "payment-failed", groupId = "notification-service-group")
    public void handlePaymentFailed(OrderEvent event) {
        log.info("🔔 Received PAYMENT_FAILED for order: {}", event.getOrderNumber());
        notificationService.sendOrderFailedNotification(event);
    }
}