package com.tech.stockmarket.stock_market_backend.services;

import com.tech.stockmarket.stock_market_backend.dto.BuyStockRequest;
import com.tech.stockmarket.stock_market_backend.dto.SellStockRequest;
import com.tech.stockmarket.stock_market_backend.entity.Portfolio;
import com.tech.stockmarket.stock_market_backend.entity.Transaction;
import com.tech.stockmarket.stock_market_backend.entity.User;
import com.tech.stockmarket.stock_market_backend.repositories.PortfolioRepository;
import com.tech.stockmarket.stock_market_backend.repositories.TransactionRepository;
import com.tech.stockmarket.stock_market_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired private PortfolioRepository portfolioRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private StockService stockService;
    @Autowired
    private TransactionRepository transactionRepository;

    public void buyStock(BuyStockRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        double price = stockService.getStockPrice(request.getStockSymbol());
        double totalCost = price * request.getQuantity();

        if (user.getBalance() < totalCost) {
            throw new RuntimeException("Insufficient balance");
        }

        Portfolio portfolio = portfolioRepository.findByUserAndStockSymbol(user, request.getStockSymbol())
                .orElse(new Portfolio());

        if (portfolio.getId() == null) {
            portfolio.setUser(user);
            portfolio.setStockSymbol(request.getStockSymbol());
            portfolio.setQuantity(request.getQuantity());
            portfolio.setAverageBuyPrice(price);
        } else {
            int totalQty = portfolio.getQuantity() + request.getQuantity();
            double newAvg = ((portfolio.getAverageBuyPrice() * portfolio.getQuantity()) + totalCost) / totalQty;

            portfolio.setQuantity(totalQty);
            portfolio.setAverageBuyPrice(newAvg);
        }

        portfolioRepository.save(portfolio);
        user.setBalance(user.getBalance() - totalCost);
        userRepository.save(user);
    }

    public List<Portfolio> getPortfolio(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return portfolioRepository.findByUser(user);
    }

    public void sellStock(String username, SellStockRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Portfolio portfolio = portfolioRepository
                .findByUserAndStockSymbol(user, request.getStockSymbol())
                .orElseThrow(() -> new RuntimeException("Stock not found in portfolio"));

        if (portfolio.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough shares to sell");
        }

        double livePrice = stockService.getStockPrice(request.getStockSymbol());
        double totalGain = livePrice * request.getQuantity();

        // Update portfolio
        portfolio.setQuantity(portfolio.getQuantity() - request.getQuantity());
        if (portfolio.getQuantity() == 0) {
            portfolioRepository.delete(portfolio);
        } else {
            portfolioRepository.save(portfolio);
        }

        // Update user balance
        user.setBalance(user.getBalance() + totalGain);
        userRepository.save(user);

        // Save transaction
        Transaction txn = new Transaction(user, request.getStockSymbol(), request.getQuantity(), livePrice, "SELL");
        transactionRepository.save(txn);
    }
}