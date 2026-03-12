package com.shopstore.service;

import com.shopstore.model.Payment;

public interface PaymentService {
    Payment pay(Long orderId);
}
