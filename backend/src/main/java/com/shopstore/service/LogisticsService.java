package com.shopstore.service;

import com.shopstore.dto.ShipRequest;
import com.shopstore.model.Shipment;

public interface LogisticsService {
    Shipment ship(ShipRequest request);
}
