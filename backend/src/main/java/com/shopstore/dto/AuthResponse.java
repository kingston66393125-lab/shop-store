package com.shopstore.dto;

public record AuthResponse(String token, Long userId, String username) {
}
