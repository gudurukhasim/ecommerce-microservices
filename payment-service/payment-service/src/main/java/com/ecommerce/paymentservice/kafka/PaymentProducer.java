package com.ecommerce.paymentservice.kafka;

import com.ecommerce.paymentservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {

    private static final Logger log = LoggerFactory.getLogger(PaymentProducer.class);

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentSuccess(OrderEvent event) {
        log.debug("Sending PAYMENT_SUCCESS for order: {}", event.getOrderNumber());
        kafkaTemplate.send("payment-success", event.getOrderNumber(), event);
    }

    public void sendPaymentFailed(OrderEvent event) {
        log.debug("Sending PAYMENT_FAILED for order: {}", event.getOrderNumber());
        kafkaTemplate.send("payment-failed", event.getOrderNumber(), event);
    }
}