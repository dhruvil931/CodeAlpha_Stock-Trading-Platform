package com.dhruvil.Stock.Trading.Platform.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockRequestDto {

    private String symbol;

    private String companyName;

    private BigDecimal price;

    private int quantity;
}
