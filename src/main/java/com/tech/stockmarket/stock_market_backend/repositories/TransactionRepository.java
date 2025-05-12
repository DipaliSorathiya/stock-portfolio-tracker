package com.tech.stockmarket.stock_market_backend.repositories;

import com.tech.stockmarket.stock_market_backend.entity.Transaction;
import com.tech.stockmarket.stock_market_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}