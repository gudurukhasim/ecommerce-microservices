package com.ecommerce.orderservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Constructors
    public Order() {}

    public Order(Long id, String orderNumber, String userEmail, Double totalAmount,
                 String status, List<OrderItem> items, LocalDateTime createdAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.userEmail = userEmail;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = items;
        this.createdAt = createdAt;
    }

    // Getters
    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public String getUserEmail() { return userEmail; }
    public Double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setStatus(String status) { this.status = status; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}