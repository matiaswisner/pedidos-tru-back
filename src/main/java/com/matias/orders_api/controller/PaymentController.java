//PaymentController.java
package com.matias.orders_api.controller;

import com.matias.orders_api.dto.payment.PaymentRequest;
import com.matias.orders_api.entity.Payment;
import com.matias.orders_api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest request) {
        try {
            Payment payment = paymentService.createPayment(request);

            if (payment.getMetodo().name().equalsIgnoreCase("EFECTIVO")) {
                return ResponseEntity.ok(Map.of(
                        "message", "Pago registrado como efectivo",
                        "estado", payment.getEstado()
                ));
            }

            return ResponseEntity.ok(Map.of(
                    "paymentUrl", payment.getPreferenceId(),
                    "paymentId", payment.getPaymentId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}