package com.shopstore.service.impl;

import com.shopstore.dto.AuthRequest;
import com.shopstore.dto.AuthResponse;
import com.shopstore.model.User;
import com.shopstore.repository.UserRepository;
import com.shopstore.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final Map<String, Long> tokenStore = new ConcurrentHashMap<>();

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "用户名已存在");
        });
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        User saved = userRepository.save(user);
        return issueToken(saved);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.username())
                .filter(u -> u.getPassword().equals(request.password()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误"));
        return issueToken(user);
    }

    private AuthResponse issueToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, user.getId());
        return new AuthResponse(token, user.getId(), user.getUsername());
    }
}
