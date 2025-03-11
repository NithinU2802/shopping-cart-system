package com.infy.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orderitems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderItemId;
	
	Integer orderId;
	Long productId;
	Integer quantity;
	
	Double price;
	
	
}
