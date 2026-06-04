package com.ecommerce.orderservice.kafka;

import com.ecommerce.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderPlacedEvent(OrderEvent event) {
        log.debug("Sending ORDER_PLACED event for order: {}", event.getOrderNumber());
        kafkaTemplate.send("order-placed", event.getOrderNumber(), event);
        log.debug("ORDER_PLACED event sent successfully");
    }
}