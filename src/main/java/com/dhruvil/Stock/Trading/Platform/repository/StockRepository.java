package com.dhruvil.Stock.Trading.Platform.repository;

import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}