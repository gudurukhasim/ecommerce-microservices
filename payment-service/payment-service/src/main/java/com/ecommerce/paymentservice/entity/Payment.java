package com.ecommerce.paymentservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private LocalDateTime processedAt;

    @PrePersist
    protected void onCreate() {
        processedAt = LocalDateTime.now();
    }

    // Constructors
    public Payment() {}

    public Payment(Long id, String orderNumber, String userEmail,
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

        public Payment build() {
            Payment p = new Payment();
            p.id = this.id;
            p.orderNumber = this.orderNumber;
            p.userEmail = this.userEmail;
            p.amount = this.amount;
            p.status = this.status;
            p.transactionId = this.transactionId;
            p.processedAt = this.processedAt;
            return p;
        }
    }
}