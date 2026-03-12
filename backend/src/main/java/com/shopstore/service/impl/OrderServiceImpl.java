package com.shopstore.service.impl;

import com.shopstore.dto.CreateOrderRequest;
import com.shopstore.model.*;
import com.shopstore.repository.BookOrderRepository;
import com.shopstore.repository.BookRepository;
import com.shopstore.repository.UserRepository;
import com.shopstore.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderService {
    private final BookOrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(BookOrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookOrder create(CreateOrderRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "用户不存在"));
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "图书不存在"));

        if (book.getStock() < request.quantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "库存不足");
        }
        book.setStock(book.getStock() - request.quantity());

        BookOrder order = new BookOrder();
        order.setUser(user);
        order.setBook(book);
        order.setQuantity(request.quantity());
        order.setTotalPrice(book.getPrice().multiply(java.math.BigDecimal.valueOf(request.quantity())));
        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);
    }
}
