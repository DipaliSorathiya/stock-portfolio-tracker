package com.tech.stockmarket.stock_market_backend.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PortfolioOverviewDTO {

    private String stockSymbol;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private double gainLoss;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getGainLoss() {
        return gainLoss;
    }

    // ✅ Setters
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setGainLoss(double gainLoss) {
        this.gainLoss = gainLoss;
    }

}
