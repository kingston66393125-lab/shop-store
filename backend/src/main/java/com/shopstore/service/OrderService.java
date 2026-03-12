package com.shopstore.service;

import com.shopstore.dto.CreateOrderRequest;
import com.shopstore.model.BookOrder;

public interface OrderService {
    BookOrder create(CreateOrderRequest request);
}
