package com.dhruvil.Stock.Trading.Platform.service;

import com.dhruvil.Stock.Trading.Platform.dto.BuyStockRequestDto;
import com.dhruvil.Stock.Trading.Platform.dto.PortfolioResponseDto;
import com.dhruvil.Stock.Trading.Platform.entities.Portfolio;
import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import com.dhruvil.Stock.Trading.Platform.entities.Transaction;
import com.dhruvil.Stock.Trading.Platform.entities.User;
import com.dhruvil.Stock.Trading.Platform.repository.PortfolioRepository;
import com.dhruvil.Stock.Trading.Platform.repository.StockRepository;
import com.dhruvil.Stock.Trading.Platform.repository.TransactionRepository;
import com.dhruvil.Stock.Trading.Platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final TransactionRepository transactionRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Transactional
    public String buyStock(String username, BuyStockRequestDto req) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Stock stock = stockRepository.findById(req.getStockId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        if(stock.getQuantity() < req.getQuantity()) {
            throw new RuntimeException("Insufficient stock quantity");
        }

        BigDecimal totalPrice = stock.getPrice().multiply(BigDecimal.valueOf(req.getQuantity()));

        if(user.getBalance().compareTo(totalPrice) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance().subtract(totalPrice));

        stock.setQuantity(stock.getQuantity() - req.getQuantity());

        Portfolio portfolio = portfolioRepository
                .findByUserAndStock(user, stock)
                .orElse(new Portfolio());

        portfolio.setUser(user);
        portfolio.setStock(stock);

        portfolio.setQuantity(
                portfolio.getQuantity() + req.getQuantity()
        );

        portfolioRepository.save(portfolio);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setStock(stock);
        transaction.setQuantity(req.getQuantity());
        transaction.setPrice(stock.getPrice());
        transaction.setType("BUY");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Stock purchased successfully";
    }

    @Transactional
    public String sellStock(String username, BuyStockRequestDto req) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Stock stock = stockRepository.findById(req.getStockId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        Portfolio portfolio = portfolioRepository
                .findByUserAndStock(user, stock)
                .orElseThrow(() -> new RuntimeException("Stock not owned"));

        if(portfolio.getQuantity() < req.getQuantity()) {
            throw new RuntimeException("Insufficient owned quantity");
        }

        BigDecimal totalPrice = stock.getPrice().multiply(BigDecimal.valueOf(req.getQuantity()));

        user.setBalance(user.getBalance().add(totalPrice));

        portfolio.setQuantity(portfolio.getQuantity() - req.getQuantity());

        stock.setQuantity(stock.getQuantity() + req.getQuantity());

        if(portfolio.getQuantity() == 0) {
            portfolioRepository.delete(portfolio);
        }
        else {
            portfolioRepository.save(portfolio);
        }

        Transaction transaction = new Transaction();

        transaction.setUser(user);
        transaction.setStock(stock);
        transaction.setQuantity(req.getQuantity());
        transaction.setPrice(stock.getPrice());
        transaction.setType("SELL");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Stock sold successfully";
    }

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
}
