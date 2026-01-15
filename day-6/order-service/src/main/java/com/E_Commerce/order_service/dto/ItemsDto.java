package com.E_Commerce.order_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDto {
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
}
