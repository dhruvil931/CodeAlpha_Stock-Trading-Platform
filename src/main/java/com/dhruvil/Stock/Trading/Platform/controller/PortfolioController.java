package com.dhruvil.Stock.Trading.Platform.controller;

import com.dhruvil.Stock.Trading.Platform.dto.PortfolioResponseDto;
import com.dhruvil.Stock.Trading.Platform.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping("/portfolio")
    public ResponseEntity<List<PortfolioResponseDto>> viewPortfolio(Principal principal) {
        return ResponseEntity.ok(portfolioService.getPortfolio(principal.getName()));
    }
}
