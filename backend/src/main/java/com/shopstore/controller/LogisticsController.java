package com.shopstore.controller;

import com.shopstore.dto.ShipRequest;
import com.shopstore.model.Shipment;
import com.shopstore.service.LogisticsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsController {
    private final LogisticsService logisticsService;

    public LogisticsController(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }

    @PostMapping("/ship")
    public Shipment ship(@Valid @RequestBody ShipRequest request) {
        return logisticsService.ship(request);
    }
}
