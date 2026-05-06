package com.dhruvil.Stock.Trading.Platform.repository;

import com.dhruvil.Stock.Trading.Platform.entities.Portfolio;
import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import com.dhruvil.Stock.Trading.Platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
}