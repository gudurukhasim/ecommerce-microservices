package com.ecommerce.orderservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String userEmail;
    private Double totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    public OrderResponse() {}

    public OrderResponse(Long id, String orderNumber, String userEmail,
                         Double totalAmount, String status,
                         LocalDateTime createdAt, List<OrderItemResponse> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.userEmail = userEmail;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }

    // Getters
    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public String getUserEmail() { return userEmail; }
    public Double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItemResponse> getItems() { return items; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }

    public static class OrderItemResponse {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double price;

        public OrderItemResponse() {}

        public OrderItemResponse(Long productId, String productName,
                                  Integer quantity, Double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
    }
}