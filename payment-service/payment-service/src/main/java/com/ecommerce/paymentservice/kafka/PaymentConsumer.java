package com.ecommerce.paymentservice.kafka;

import com.ecommerce.paymentservice.dto.OrderEvent;
import com.ecommerce.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    private final PaymentService paymentService;

    public PaymentConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "order-placed", groupId = "payment-service-group")
    public void handleOrderPlaced(OrderEvent event) {
        log.debug("Received ORDER_PLACED event for: {}", event.getOrderNumber());
        paymentService.processPayment(event);
    }
}