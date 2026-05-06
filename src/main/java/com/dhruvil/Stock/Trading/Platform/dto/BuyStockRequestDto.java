package com.dhruvil.Stock.Trading.Platform.dto;

import lombok.Data;

@Data
public class BuyStockRequestDto {
    private Long stockId;

    private int quantity;
}
