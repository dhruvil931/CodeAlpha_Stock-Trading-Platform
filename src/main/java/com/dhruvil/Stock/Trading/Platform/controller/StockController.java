package com.dhruvil.Stock.Trading.Platform.controller;

import com.dhruvil.Stock.Trading.Platform.dto.BuyStockRequestDto;
import com.dhruvil.Stock.Trading.Platform.dto.PortfolioResponseDto;
import com.dhruvil.Stock.Trading.Platform.entities.Stock;
import com.dhruvil.Stock.Trading.Platform.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(Principal principal, @RequestBody BuyStockRequestDto buyStockRequestDto) {
        String result = stockService.buyStock(principal.getName(), buyStockRequestDto);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellStock(@RequestBody BuyStockRequestDto buyStockRequestDto, Principal principal) {
        String result = stockService.sellStock(principal.getName(), buyStockRequestDto);

        return ResponseEntity.ok(result);
    }
}
