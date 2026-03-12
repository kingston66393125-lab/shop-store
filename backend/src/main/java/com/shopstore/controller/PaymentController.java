package com.shopstore.controller;

import com.shopstore.dto.PaymentRequest;
import com.shopstore.model.Payment;
import com.shopstore.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment pay(@Valid @RequestBody PaymentRequest request) {
        return paymentService.pay(request.orderId());
    }
}
