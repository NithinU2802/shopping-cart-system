package com.infy.order.dto;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long orderItemId;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}
