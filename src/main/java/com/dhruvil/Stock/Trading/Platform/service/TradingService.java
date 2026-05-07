package com.dhruvil.Stock.Trading.Platform.service;

import com.dhruvil.Stock.Trading.Platform.dto.PortfolioResponseDto;
import com.dhruvil.Stock.Trading.Platform.dto.TransactionResponseDto;
import com.dhruvil.Stock.Trading.Platform.entities.Portfolio;
import com.dhruvil.Stock.Trading.Platform.entities.Transaction;
import com.dhruvil.Stock.Trading.Platform.entities.User;
import com.dhruvil.Stock.Trading.Platform.repository.PortfolioRepository;
import com.dhruvil.Stock.Trading.Platform.repository.TransactionRepository;
import com.dhruvil.Stock.Trading.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradingService {
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final TransactionRepository transactionRepository;

    public List<PortfolioResponseDto> getPortfolio(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        List<Portfolio> portfolios = portfolioRepository.findByUser(user);

        return portfolios.stream()
                .map(p -> {
                    BigDecimal totalValue = p.getStock().getPrice()
                            .multiply(BigDecimal.valueOf(p.getQuantity()));

                    return new PortfolioResponseDto(
                            p.getStock().getSymbol(),
                            p.getStock().getCompanyName(),
                            p.getQuantity(),
                            p.getStock().getPrice(),
                            totalValue
                    );
                })
                .toList();
    }

    public List<TransactionResponseDto> getTransactions(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Transaction> transactions = transactionRepository.findByUser(user);

        return transactions.stream()
                .map(t -> new TransactionResponseDto(
                        t.getStock().getSymbol(),
                        t.getStock().getCompanyName(),
                        t.getQuantity(),
                        t.getPrice(),
                        t.getType(),
                        t.getTimestamp()
                ))
                .toList();
    }
}
