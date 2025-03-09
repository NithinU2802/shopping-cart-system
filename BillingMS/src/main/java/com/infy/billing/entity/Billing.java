package com.infy.billing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Billing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer billingId;
	
	Integer orderId;
	//Double discount;
	Double finalAmount;
	String paymentMethod;
	String paymentStatus;
	Long couponId;
	
	
}
