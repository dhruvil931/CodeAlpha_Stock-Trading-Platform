package com.dhruvil.Stock.Trading.Platform.repository;

import com.dhruvil.Stock.Trading.Platform.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}