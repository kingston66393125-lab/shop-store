package com.shopstore.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentRequest(@NotNull Long orderId) {
}
