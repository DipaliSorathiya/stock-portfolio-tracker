package com.tech.stockmarket.stock_market_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.tech.stockmarket.stock_market_backend")
@EnableCaching
public class StockMarketBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketBackendApplication.class, args);
	}

}
