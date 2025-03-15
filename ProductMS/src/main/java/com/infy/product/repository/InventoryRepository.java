package com.infy.product.repository;

import java.util.List;
import java.util.Optional;

import com.infy.product.dto.InventoryReplenishmentDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.product.entity.Inventory;
import com.infy.product.entity.Product;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	@Query("SELECT I from Inventory I where I.productId = ?1")
	Optional<Inventory> findByProductId(Long productId);

	@Query("SELECT new com.infy.product.dto.InventoryReplenishmentDTO(I.productId, I.repThreshold - I.quantityInStock) FROM Inventory I WHERE I.quantityInStock < I.repThreshold")
	List<InventoryReplenishmentDTO> getProductsToBeReplenished();
	
//	Inventory findByProduct(Product product);
}
