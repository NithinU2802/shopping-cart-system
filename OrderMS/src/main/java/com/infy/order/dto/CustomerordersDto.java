package com.infy.order.dto;

import java.time.LocalDateTime;


import lombok.Data;


@Data
public class CustomerordersDto {
	
	
	Integer orderId;
	
	CustomerDto customerDto;
	LocalDateTime orderDate;
	
	Double totalAmount;
	String orderStatus;
}
