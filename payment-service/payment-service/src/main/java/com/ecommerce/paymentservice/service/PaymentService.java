package com.ecommerce.paymentservice.service;

import com.ecommerce.paymentservice.dto.OrderEvent;
import com.ecommerce.paymentservice.dto.PaymentResponse;
import com.ecommerce.paymentservice.entity.Payment;
import com.ecommerce.paymentservice.kafka.PaymentProducer;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    @Value("${payment.success-rate}")
    private int successRate;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentProducer paymentProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentProducer = paymentProducer;
    }

    public void processPayment(OrderEvent event) {
        log.debug("Processing payment for order: {}", event.getOrderNumber());

        boolean isSuccess = (int)(Math.random() * 100) < successRate;

        String transactionId = "TXN-" + UUID.randomUUID()
                .toString().substring(0, 8).toUpperCase();

        String status = isSuccess ? "SUCCESS" : "FAILED";

        Payment payment = Payment.builder()
                .orderNumber(event.getOrderNumber())
                .userEmail(event.getUserEmail())
                .amount(event.getTotalAmount())
                .status(status)
                .transactionId(transactionId)
                .build();

        paymentRepository.save(payment);
        log.debug("Payment {} for order: {}", status, event.getOrderNumber());

        event.setStatus(status);

        if (isSuccess) {
            paymentProducer.sendPaymentSuccess(event);
        } else {
            paymentProducer.sendPaymentFailed(event);
        }
    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PaymentResponse getPaymentByOrderNumber(String orderNumber) {
        Payment payment = paymentRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found for order: " + orderNumber));
        return mapToResponse(payment);
    }

    public List<PaymentResponse> getPaymentsByUser(String userEmail) {
        return paymentRepository.findByUserEmail(userEmail)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderNumber(payment.getOrderNumber())
                .userEmail(payment.getUserEmail())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .processedAt(payment.getProcessedAt())
                .build();
    }
}