package com.dhruvil.Stock.Trading.Platform.controller;

import com.dhruvil.Stock.Trading.Platform.dto.AuthRequestDto;
import com.dhruvil.Stock.Trading.Platform.repository.UserRepository;
import com.dhruvil.Stock.Trading.Platform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto req) {
        return ResponseEntity.ok(authService.login(req));
    }
}
