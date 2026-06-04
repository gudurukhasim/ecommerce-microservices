package com.ecommerce.paymentservice.dto;

import java.util.List;

public class OrderEvent {

    private String orderNumber;
    private String userEmail;
    private Double totalAmount;
    private String status;
    private List<OrderItemInfo> items;

    public OrderEvent() {}

    public OrderEvent(String orderNumber, String userEmail,
                      Double totalAmount, String status,
                      List<OrderItemInfo> items) {
        this.orderNumber = orderNumber;
        this.userEmail = userEmail;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = items;
    }

    // Getters
    public String getOrderNumber() { return orderNumber; }
    public String getUserEmail() { return userEmail; }
    public Double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public List<OrderItemInfo> getItems() { return items; }

    // Setters
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setStatus(String status) { this.status = status; }
    public void setItems(List<OrderItemInfo> items) { this.items = items; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String orderNumber;
        private String userEmail;
        private Double totalAmount;
        private String status;
        private List<OrderItemInfo> items;

        public Builder orderNumber(String orderNumber) { this.orderNumber = orderNumber; return this; }
        public Builder userEmail(String userEmail) { this.userEmail = userEmail; return this; }
        public Builder totalAmount(Double totalAmount) { this.totalAmount = totalAmount; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder items(List<OrderItemInfo> items) { this.items = items; return this; }

        public OrderEvent build() {
            return new OrderEvent(orderNumber, userEmail, totalAmount, status, items);
        }
    }

    // Inner class
    public static class OrderItemInfo {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double price;

        public OrderItemInfo() {}

        public OrderItemInfo(Long productId, String productName,
                             Integer quantity, Double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public Long getProductId() { return productId; }
        public String getProductName() { return productName; }
        public Integer getQuantity() { return quantity; }
        public Double getPrice() { return price; }

        public void setProductId(Long productId) { this.productId = productId; }
        public void setProductName(String productName) { this.productName = productName; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public void setPrice(Double price) { this.price = price; }
    }
}