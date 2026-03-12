package com.shopstore.controller;

import com.shopstore.dto.CreateOrderRequest;
import com.shopstore.model.BookOrder;
import com.shopstore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public BookOrder create(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.create(request);
    }
}
