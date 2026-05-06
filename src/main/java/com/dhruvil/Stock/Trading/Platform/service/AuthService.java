package com.dhruvil.Stock.Trading.Platform.service;

import com.dhruvil.Stock.Trading.Platform.dto.AuthRequestDto;
import com.dhruvil.Stock.Trading.Platform.dto.AuthResponseDto;
import com.dhruvil.Stock.Trading.Platform.entities.User;
import com.dhruvil.Stock.Trading.Platform.repository.UserRepository;
import com.dhruvil.Stock.Trading.Platform.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto register(AuthRequestDto req) {
        if(userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return new AuthResponseDto("User registered", null);
    }

    public AuthResponseDto login(AuthRequestDto req) {
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponseDto("Login successful", token);
    }
}
