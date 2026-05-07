package com.dhruvil.Stock.Trading.Platform.repository;

import com.dhruvil.Stock.Trading.Platform.entities.Transaction;
import com.dhruvil.Stock.Trading.Platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}