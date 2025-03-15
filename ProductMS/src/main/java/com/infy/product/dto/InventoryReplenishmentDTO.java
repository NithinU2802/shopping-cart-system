package com.infy.product.dto;

import lombok.Data;

@Data
public class InventoryReplenishmentDTO {
    private Long productId;
    private Integer quantityToReplenish;

//    public InventoryReplenishmentDTO(Long productId, Integer quantityToReplenish){
//        this.productId = productId;
//        this.quantityToReplenish = quantityToReplenish;
//    }
}
