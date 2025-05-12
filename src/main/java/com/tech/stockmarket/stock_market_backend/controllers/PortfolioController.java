package com.tech.stockmarket.stock_market_backend.controllers;

import com.tech.stockmarket.stock_market_backend.dto.BuyStockRequest;
import com.tech.stockmarket.stock_market_backend.dto.PortfolioOverviewDTO;
import com.tech.stockmarket.stock_market_backend.dto.SellStockRequest;
import com.tech.stockmarket.stock_market_backend.entity.Portfolio;
import com.tech.stockmarket.stock_market_backend.entity.User;
import com.tech.stockmarket.stock_market_backend.repositories.PortfolioRepository;
import com.tech.stockmarket.stock_market_backend.repositories.UserRepository;
import com.tech.stockmarket.stock_market_backend.services.PortfolioService;
import com.tech.stockmarket.stock_market_backend.services.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/portfolio")
@SecurityRequirement(name = "bearerAuth")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StockService stockService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(@RequestBody BuyStockRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("🎯 Inside /buy API for: " + username);

        portfolioService.buyStock(request,username);
        return ResponseEntity.ok("✅ Stock bought and saved to DB.");
    }

    @GetMapping
    public ResponseEntity<List<Portfolio>> getPortfolio() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("📦 Fetching portfolio for user: " + username);

        List<Portfolio> portfolioList = portfolioService.getPortfolio(username);
        return new ResponseEntity<>(portfolioList, HttpStatus.OK);
    }

    @PostMapping("/sell")
    @Operation(summary = "Sell a stock from user portfolio", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<String> sellStock(@RequestBody SellStockRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        portfolioService.sellStock(username, request);
        return ResponseEntity.ok("Stock sold successfully.");
    }

    @GetMapping("/overview")
    public ResponseEntity<List<PortfolioOverviewDTO>> getPortfolioOverview() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Portfolio> portfolios = portfolioRepository.findByUser(user);

        List<PortfolioOverviewDTO> overviewList = portfolios.stream().map(portfolio -> {
            double livePrice = stockService.getStockPrice(portfolio.getStockSymbol());
            double gainLoss = (livePrice - portfolio.getAverageBuyPrice()) * portfolio.getQuantity();

            PortfolioOverviewDTO dto = new PortfolioOverviewDTO();
            dto.setStockSymbol(portfolio.getStockSymbol());
            dto.setQuantity(portfolio.getQuantity());
            dto.setBuyPrice(portfolio.getAverageBuyPrice());
            dto.setCurrentPrice(livePrice);
            dto.setGainLoss(gainLoss);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(overviewList);
    }
}
