package com.dhruvil.Stock.Trading.Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private String symbol;
    private String companyName;
    private int quantity;
    private BigDecimal price;
    private String type;
    private LocalDateTime timestamp;
}
