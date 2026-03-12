package com.shopstore.service.impl;

import com.shopstore.dto.ShipRequest;
import com.shopstore.model.*;
import com.shopstore.repository.BookOrderRepository;
import com.shopstore.repository.ShipmentRepository;
import com.shopstore.service.LogisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LogisticsServiceImpl implements LogisticsService {
    private final BookOrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public LogisticsServiceImpl(BookOrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment ship(ShipRequest request) {
        BookOrder order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "订单不存在"));
        if (order.getStatus() != OrderStatus.PAID) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "仅支持已支付订单发货");
        }
        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setLogisticsCompany(request.logisticsCompany());
        shipment.setTrackingNumber(request.trackingNumber());
        shipment.setStatus("IN_TRANSIT");
        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
        return shipmentRepository.save(shipment);
    }
}
