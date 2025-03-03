package com.infy.billing.dto;

import lombok.Data;

@Data
public class BillingDto {

	Integer billingId;
	
	CustomerordersDto customerOrderstDto;
	Double discount;
	Double finalAmount;
	String paymentMethod;
	String paymentStatus;
	CouponDto couponDto;
}
