package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.*;
import com.ecommerce.orderservice.entity.*;
import com.ecommerce.orderservice.kafka.OrderProducer;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        String orderNumber = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Double total = request.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setUserEmail(request.getUserEmail());
        order.setTotalAmount(total);
        order.setStatus("PENDING");

        List<OrderItem> items = request.getItems().stream()
                .map(i -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setProductId(i.getProductId());
                    item.setProductName(i.getProductName());
                    item.setQuantity(i.getQuantity());
                    item.setPrice(i.getPrice());
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(items);
        Order saved = orderRepository.save(order);

        List<OrderEvent.OrderItemInfo> eventItems = request.getItems().stream()
                .map(i -> new OrderEvent.OrderItemInfo(
                        i.getProductId(),
                        i.getProductName(),
                        i.getQuantity(),
                        i.getPrice()))
                .collect(Collectors.toList());

        OrderEvent event = new OrderEvent(orderNumber, request.getUserEmail(),
                total, "ORDER_PLACED", eventItems);

        orderProducer.sendOrderPlacedEvent(event);
        log.debug("Order placed: {}", orderNumber);

        return mapToResponse(saved);
    }

    public List<OrderResponse> getOrdersByUser(String userEmail) {
        return orderRepository.findByUserEmail(userEmail)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public OrderResponse getOrderByNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderNumber));
        return mapToResponse(order);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderResponse.OrderItemResponse> itemResponses = order.getItems()
                .stream()
                .map(i -> new OrderResponse.OrderItemResponse(
                        i.getProductId(),
                        i.getProductName(),
                        i.getQuantity(),
                        i.getPrice()))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getUserEmail(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                itemResponses);
    }
}