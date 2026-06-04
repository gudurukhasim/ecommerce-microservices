package com.ecommerce.notificationservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String orderNumber;
    private String userEmail;
    private Double amount;
    private String type;
    private String message;
    private String status;
    private LocalDateTime sentAt;

    public Notification() {}

    public Notification(String id, String orderNumber, String userEmail,
                        Double amount, String type, String message,
                        String status, LocalDateTime sentAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.userEmail = userEmail;
        this.amount = amount;
        this.type = type;
        this.message = message;
        this.status = status;
        this.sentAt = sentAt;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id;
        private String orderNumber;
        private String userEmail;
        private Double amount;
        private String type;
        private String message;
        private String status;
        private LocalDateTime sentAt;

        public Builder id(String id) { this.id = id; return this; }
        public Builder orderNumber(String orderNumber) { this.orderNumber = orderNumber; return this; }
        public Builder userEmail(String userEmail) { this.userEmail = userEmail; return this; }
        public Builder amount(Double amount) { this.amount = amount; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder sentAt(LocalDateTime sentAt) { this.sentAt = sentAt; return this; }

        public Notification build() {
            return new Notification(id, orderNumber, userEmail,
                    amount, type, message, status, sentAt);
        }
    }
}