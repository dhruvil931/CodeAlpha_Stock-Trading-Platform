package com.dhruvil.Stock.Trading.Platform.service;

import com.dhruvil.Stock.Trading.Platform.dto.PortfolioResponseDto;
import com.dhruvil.Stock.Trading.Platform.entities.Portfolio;
import com.dhruvil.Stock.Trading.Platform.entities.User;
import com.dhruvil.Stock.Trading.Platform.repository.PortfolioRepository;
import com.dhruvil.Stock.Trading.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

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
