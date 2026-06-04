package com.ecommerce.paymentservice.controller;

import com.ecommerce.paymentservice.dto.PaymentResponse;
import com.ecommerce.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/order/{orderNumber}")
    public ResponseEntity<PaymentResponse> getByOrderNumber(
            @PathVariable String orderNumber) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderNumber(orderNumber));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<PaymentResponse>> getByUser(
            @PathVariable String email) {
        return ResponseEntity.ok(paymentService.getPaymentsByUser(email));
    }
}