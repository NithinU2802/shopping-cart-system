package com.infy.order.dto;


import lombok.Data;


@Data
public class OrderitemsDto {
	
	
	Integer orderItemId;
	
	CustomerordersDto customerOrderDto;
	
	ProductDto productDto;
	
	Integer quantity;
	
	Double price;
	
	
}
