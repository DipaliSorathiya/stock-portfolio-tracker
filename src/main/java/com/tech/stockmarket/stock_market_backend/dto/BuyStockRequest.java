package com.tech.stockmarket.stock_market_backend.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


public class BuyStockRequest {

    private String stockSymbol;
    private int quantity;

    public BuyStockRequest() {}

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