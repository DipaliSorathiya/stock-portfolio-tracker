package com.tech.stockmarket.stock_market_backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;


@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockSymbol;
    private int quantity;
    private double averageBuyPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // ✅ This field was missing

    public Portfolio() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}