package com.ecommerce.orderservice.kafka;

import com.ecommerce.orderservice.dto.OrderEvent;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);
    private final OrderRepository orderRepository;

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "payment-success", groupId = "order-service-group")
    public void handlePaymentSuccess(OrderEvent event) {
        log.debug("Received PAYMENT_SUCCESS for order: {}", event.getOrderNumber());
        Order order = orderRepository.findByOrderNumber(event.getOrderNumber())
                .orElseThrow(() -> new RuntimeException("Order not found: " + event.getOrderNumber()));
        order.setStatus("CONFIRMED");
        orderRepository.save(order);
    }

    @KafkaListener(topics = "payment-failed", groupId = "order-service-group")
    public void handlePaymentFailed(OrderEvent event) {
        log.debug("Received PAYMENT_FAILED for order: {}", event.getOrderNumber());
        Order order = orderRepository.findByOrderNumber(event.getOrderNumber())
                .orElseThrow(() -> new RuntimeException("Order not found: " + event.getOrderNumber()));
        order.setStatus("FAILED");
        orderRepository.save(order);
    }
}