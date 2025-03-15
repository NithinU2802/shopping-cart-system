package com.infy.product.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InventoryDto {

	Long id;
	
	ProductDto productDto;
	
	Integer quantityInStock;
	Integer quantitySold;
	Integer repThreshold;
	LocalDateTime lastReplenishmentDate;

}
