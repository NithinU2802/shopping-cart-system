package com.infy.order.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDto {

	Long id;
	
	String name;
	String category;
	String manufacturer;
	String useCase;
	Double price;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
}
