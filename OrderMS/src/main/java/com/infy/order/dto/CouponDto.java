package com.infy.order.dto;

import java.time.LocalDateTime;


import lombok.Data;


@Data
public class CouponDto {
	
	
	Long id;
	
	String code;
	Double discountPercentage;
	LocalDateTime validUntil;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
	
}
