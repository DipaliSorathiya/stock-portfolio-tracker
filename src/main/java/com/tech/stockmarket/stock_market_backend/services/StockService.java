package com.tech.stockmarket.stock_market_backend.services;


import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;


@Service
public class StockService {

    private final String API_KEY = "DTZYCJ2L011JGU80";
    private final String BASE_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol={symbol}&apikey={apikey}";


    @Cacheable(value = "stockPrices", key = "#symbol")
    public double getStockPrice(String symbol) {
        System.out.println("📡 Fetching stock price for " + symbol + " from external API (not Redis)");

        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL.replace("{symbol}", symbol).replace("{apikey}", API_KEY);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            JSONObject json = new JSONObject(response.getBody());
            JSONObject quote = json.getJSONObject("Global Quote");
            return Double.parseDouble(quote.getString("05. price"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock price");
        }
    }
}
