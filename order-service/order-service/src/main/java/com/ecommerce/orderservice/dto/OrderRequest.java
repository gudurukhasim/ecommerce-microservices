package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class OrderRequest {

    @NotBlank(message = "User email is required")
    private String userEmail;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemRequest> items;

    public OrderRequest() {}

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }

    public static class OrderItemRequest {
        @NotNull
        private Long productId;

        @NotBlank
        private String productName;

        @NotNull @Positive
        private Integer quantity;

        @NotNull @Positive
        private Double price;

        public OrderItemRequest() {}

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