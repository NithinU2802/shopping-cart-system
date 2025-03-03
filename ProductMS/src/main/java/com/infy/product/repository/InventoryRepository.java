package com.infy.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.product.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	@Query("SELECT I from Inventory I where I.productId = ?1")
	Optional<Inventory> findByProductId(Long productId);
}
