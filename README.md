 Stock Market Portfolio Tracker (Spring Boot + JWT + Swagger)

A full-stack-ready backend system to simulate a **real-time stock trading platform**. It supports user registration, JWT-secured authentication, live stock buying/selling, portfolio insights, and transaction tracking.

---

##  Features

### 🔐 Authentication

* Secure user registration & login
* JWT-based token system
* BCrypt password hashing

### 💼 Portfolio Management

* Buy/Sell stocks (real-time price via Alpha Vantage API)
* Add funds to account
* Get portfolio overview (gain/loss)
* Track holdings with live price & % change

### 📊 Insights & History

* Transaction history (BUY / SELL logs)
* Balance viewing
* Portfolio summary statistics

### 📚 Documentation

* Full Swagger/OpenAPI integration
* Try endpoints live from browser

---

## 🧱 Tech Stack

| Layer      | Technology                     |
| ---------- | ------------------------------ |
| Language   | Java 17                        |
| Framework  | Spring Boot 3.x                |
| Security   | Spring Security, JWT           |
| Database   | H2 / MySQL                     |
| API Client | RestTemplate (for stock price) |
| Docs       | Swagger (springdoc-openapi)    |
| Build Tool | Maven                          |

---

## 📁 Project Structure

```
src/main/java/com/tech/stockmarket
👉 config
👉 controllers
👉 dto
👉 entity
👉 repositories
👉 security
👉 services
👉 StockMarketBackendApplication.java
```

---

##  Key Endpoints

### 🔐 Auth APIs

```
POST   /api/auth/register      // Register new user
POST   /api/auth/login         // Login & get JWT token
```

### 💸 User APIs

```
POST   /api/users/add-funds     // Add funds to balance
GET    /api/users/balance       // Check available balance
```

### 📦 Portfolio APIs

```
POST   /api/portfolio/buy        // Buy stock
POST   /api/portfolio/sell       // Sell stock
GET    /api/portfolio/overview   // View current portfolio
GET    /api/portfolio/summary    // Get total invested, value, gain/loss
GET    /api/portfolio/holdings   // View current holdings and % gain
```

### 📄 Transactions

```
GET    /api/transactions         // All buy/sell transactions
```

---

## 🔐 How Authentication Works

1. Register with `/api/auth/register`
2. Login via `/api/auth/login` to receive a JWT token
3. Use the token in all secured requests:

```bash
Authorization: Bearer <token>
```

---

## 📊 Swagger UI

Access live documentation:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Sample CURL (Buy Stock)

```bash
curl --location 'http://localhost:8080/api/portfolio/buy' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--data '{
  "stockSymbol": "TCS.BSE",
  "quantity": 10
}'
```

---

##  Learning Highlights

* JWT token generation & validation filter
* Role-based access with Spring Security
* Live stock data fetching via RestTemplate
* Entity <-> DTO mapping for clean API design
* Swagger for developer-friendly API docs


Postman Demo – Stock Portfolio Manager Backend Project

In this video, I walk through the core backend APIs I built using Java + Spring Boot for a stock portfolio management system. 💻📊

 Tech Stack:
Java + Spring Boot

MySQL

JWT Authentication

Postman for API testing

 APIs Covered in the Demo:
 User Registration (/api/auth/register)
 User Login with JWT Token (/api/auth/login)
 Add Funds (/api/portfolio/add-funds)
 Buy Stock (/api/portfolio/buy)
 Sell Stock (/api/portfolio/sell)
 View Portfolio Overview (/api/portfolio/overview)
 Transaction History (/api/portfolio/history)

 All endpoints are secured using JWT-based authentication.

Video Link : https://drive.google.com/drive/u/0/folders/1mcO_EpG9dnyDc_2byVdDK7kc4nmomebb



