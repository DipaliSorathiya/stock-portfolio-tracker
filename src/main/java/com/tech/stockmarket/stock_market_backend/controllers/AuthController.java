package com.tech.stockmarket.stock_market_backend.controllers;

import com.tech.stockmarket.stock_market_backend.dto.JwtResponse;
import com.tech.stockmarket.stock_market_backend.entity.User;
import com.tech.stockmarket.stock_market_backend.repositories.UserRepository;
import com.tech.stockmarket.stock_market_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tech.stockmarket.stock_market_backend.dto.LoginRequest;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // 🔐 encode!
        user.setBalance(10000);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
