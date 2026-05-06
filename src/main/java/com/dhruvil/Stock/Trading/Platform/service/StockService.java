package com.dhruvil.Stock.Trading.Platform.service;

import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import com.dhruvil.Stock.Trading.Platform.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}
