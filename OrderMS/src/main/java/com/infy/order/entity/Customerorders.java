package com.infy.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customerorders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderId;
	
	Long customerId;
	LocalDateTime orderDate;
	
	Double totalAmount;
	String orderStatus;
}
