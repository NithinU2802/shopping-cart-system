package com.infy.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String code;
	Double discountPercentage;
	LocalDateTime validUntil;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
	
}
