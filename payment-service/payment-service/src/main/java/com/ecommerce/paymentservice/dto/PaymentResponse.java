package com.ecommerce.paymentservice.dto;

import java.time.LocalDateTime;

public class PaymentResponse {

    private Long id;
    private String orderNumber;
    private String userEmail;
    private Double amount;
    private String status;
    private String transactionId;
    private LocalDateTime processedAt;

    public PaymentResponse() {}

    public PaymentResponse(Long id, String orderNumber, String userEmail,
                           Double amount, String status, String transactionId,
                           LocalDateTime processedAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.userEmail = userEmail;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
        this.processedAt = processedAt;
    }

    // Getters
    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public String getUserEmail() { return userEmail; }
    public Double getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getTransactionId() { return transactionId; }
    public LocalDateTime getProcessedAt() { return processedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String orderNumber;
        private String userEmail;
        private Double amount;
        private String status;
        private String transactionId;
        private LocalDateTime processedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder orderNumber(String orderNumber) { this.orderNumber = orderNumber; return this; }
        public Builder userEmail(String userEmail) { this.userEmail = userEmail; return this; }
        public Builder amount(Double amount) { this.amount = amount; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder transactionId(String transactionId) { this.transactionId = transactionId; return this; }
        public Builder processedAt(LocalDateTime processedAt) { this.processedAt = processedAt; return this; }

        public PaymentResponse build() {
            return new PaymentResponse(id, orderNumber, userEmail,
                    amount, status, transactionId, processedAt);
        }
    }
}