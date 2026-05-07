package com.dhruvil.Stock.Trading.Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResponseDto {
    private String symbol;

    private String companyName;

    private int quality;

    private BigDecimal currentPrice;

    private BigDecimal totalValue;
}
