package com.shopstore.service.impl;

import com.shopstore.model.*;
import com.shopstore.repository.BookOrderRepository;
import com.shopstore.repository.PaymentRepository;
import com.shopstore.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final BookOrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(BookOrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment pay(Long orderId) {
        BookOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "订单不存在"));
        if (order.getStatus() != OrderStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "仅支持待支付订单");
        }
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setStatus(PaymentStatus.SUCCESS);

        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        return paymentRepository.save(payment);
    }
}
