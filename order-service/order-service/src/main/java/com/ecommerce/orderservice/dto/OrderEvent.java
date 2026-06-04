package com.ecommerce.orderservice.dto;

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

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItemInfo> getItems() { return items; }
    public void setItems(List<OrderItemInfo> items) { this.items = items; }

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
        public void setProductId(Long productId) { this.productId = productId; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
    }
}