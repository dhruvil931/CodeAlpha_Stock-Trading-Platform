package com.dhruvil.Stock.Trading.Platform.controller;

import com.dhruvil.Stock.Trading.Platform.dto.PortfolioResponseDto;
import com.dhruvil.Stock.Trading.Platform.dto.TransactionResponseDto;
import com.dhruvil.Stock.Trading.Platform.service.TradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradingController {
    private final TradingService tradingService;

    @PostMapping("/portfolio")
    public ResponseEntity<List<PortfolioResponseDto>> viewPortfolio(Principal principal) {
        return ResponseEntity.ok(tradingService.getPortfolio(principal.getName()));
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(Principal principal) {
        return ResponseEntity.ok(tradingService.getTransactions(principal.getName()));
    }
}
