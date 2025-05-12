package com.tech.stockmarket.stock_market_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tech.stockmarket.stock_market_backend")
public class StockMarketBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketBackendApplication.class, args);
	}

}
