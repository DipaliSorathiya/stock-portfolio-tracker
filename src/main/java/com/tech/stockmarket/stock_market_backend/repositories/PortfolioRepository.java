package com.tech.stockmarket.stock_market_backend.repositories;

import com.tech.stockmarket.stock_market_backend.entity.Portfolio;
import com.tech.stockmarket.stock_market_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserAndStockSymbol(User user, String stockSymbol);
    List<Portfolio> findByUser(User user);
}