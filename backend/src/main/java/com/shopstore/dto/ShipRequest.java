package com.shopstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShipRequest(@NotNull Long orderId, @NotBlank String logisticsCompany, @NotBlank String trackingNumber) {
}
