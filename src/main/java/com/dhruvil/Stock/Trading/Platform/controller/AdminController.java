package com.dhruvil.Stock.Trading.Platform.controller;

import com.dhruvil.Stock.Trading.Platform.dto.StockRequestDto;
import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import com.dhruvil.Stock.Trading.Platform.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/stocks")
@RequiredArgsConstructor
public class AdminController {
    private final StockRepository stockRepository;

    @Transactional
    @PostMapping
    private String addStock(StockRequestDto req) {
        if(stockRepository.findBySymbol(req.getSymbol()).isPresent()) {
            throw new RuntimeException("Stock already exists");
        }

        Stock stock = new Stock();

        stock.setSymbol(req.getSymbol());
        stock.setCompanyName(req.getCompanyName());
        stock.setPrice(req.getPrice());
        stock.setQuantity(req.getQuantity());

        stockRepository.save(stock);

        return "Stock added successfully";
    }

    @Transactional
    @DeleteMapping("{symbol}")
    public String removeStock(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        stockRepository.deleteBySymbol(symbol);

        return "Stock removed successfully";
    }
}
