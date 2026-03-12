package com.shopstore.service;

import com.shopstore.dto.AuthRequest;
import com.shopstore.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}
