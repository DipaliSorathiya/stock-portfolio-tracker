package com.tech.stockmarket.stock_market_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockSymbol;
    private int quantity;
    private double price;
    private String type; // BUY or SELL
    private LocalDateTime timestamp;

    @ManyToOne
    private User user;

    public Transaction() {}

    public Transaction(User user, String stockSymbol, int quantity, double price, String type) {
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    // Getters/Setters
    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}