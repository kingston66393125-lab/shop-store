package com.shopstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(@NotNull Long userId, @NotNull Long bookId, @Min(1) Integer quantity) {
}
