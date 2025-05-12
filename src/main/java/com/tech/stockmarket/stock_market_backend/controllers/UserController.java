package com.tech.stockmarket.stock_market_backend.controllers;
import com.tech.stockmarket.stock_market_backend.repositories.UserRepository;
import com.tech.stockmarket.stock_market_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.tech.stockmarket.stock_market_backend.entity.User;

import java.util.Map;

@RestController
@RequestMapping("/api/users") // Ensure it matches the API path in Postman
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/add-funds")
    public ResponseEntity<String> addFunds(@RequestBody Map<String, Double> request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        double amount = request.get("amount");

        User user = userRepository.findByUsername(username).orElseThrow();
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);

        return ResponseEntity.ok("Funds added. New Balance: Rs. " + user.getBalance());
    }
}
