package com.dhruvil.Stock.Trading.Platform.controller;

import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import com.dhruvil.Stock.Trading.Platform.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }
}
