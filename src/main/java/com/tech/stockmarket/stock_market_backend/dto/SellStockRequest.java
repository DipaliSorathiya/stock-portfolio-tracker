package com.tech.stockmarket.stock_market_backend.dto;

public class SellStockRequest {
    private String stockSymbol;
    private int quantity;

    // Getters & Setters
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
}
