package com.infy.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class CartDto {
    private Long cartId;
    private Long customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CartItemDto> items;
 
}

