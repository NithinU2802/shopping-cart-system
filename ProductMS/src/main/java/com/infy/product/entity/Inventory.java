package com.infy.product.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long productId;
	
	Integer quantityInStock;
	Integer quantitySold;
	LocalDateTime lastReplenishmentDate;
	
	@PreUpdate
    protected void onUpdate() {
		lastReplenishmentDate = LocalDateTime.now();
    }
	
	
}
