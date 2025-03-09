package com.infy.order.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartItemDto {
    private Long cartItemId;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;
    private LocalDateTime addedAt;
}
