package com.infy.product.dto;

import java.time.LocalDateTime;

public class InventoryDto {

	Long id;
	
	ProductDto productDto;
	
	Integer quantityInStock;
	Integer quantitySold;
	LocalDateTime lastReplenishmentDate;
}
