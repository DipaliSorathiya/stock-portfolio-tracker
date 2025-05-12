package com.tech.stockmarket.stock_market_backend.controllers;

import com.tech.stockmarket.stock_market_backend.entity.Transaction;
import com.tech.stockmarket.stock_market_backend.entity.User;
import com.tech.stockmarket.stock_market_backend.repositories.TransactionRepository;
import com.tech.stockmarket.stock_market_backend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Transaction>> getUserTransactions() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(transactionRepository.findByUser(user));
    }
}