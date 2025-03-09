package com.infy.product.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.infy.product.dto.ProductDto;
import com.infy.product.entity.Inventory;
import com.infy.product.entity.Product;
import com.infy.product.exception.ProductException;
import com.infy.product.repository.InventoryRepository;
import com.infy.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
	
	ProductRepository productRepository;
	InventoryRepository inventoryRepository;
	
	public ProductServiceImpl(ProductRepository productRepository, InventoryRepository inventoryRepository) {
		this.productRepository = productRepository;
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public ProductDto
	isProductAvailable(Long productId, Integer quantityRequired) throws ProductException {
		Optional<Product> productOpt = productRepository.findById(productId);
		if(productOpt.isEmpty()) {
			throw new ProductException("No Product found with given Id");
		}
		
		Optional<Inventory> inventOpt = inventoryRepository.findByProductId(productId);
		
		if(inventOpt.isEmpty() || inventOpt.get().getQuantityInStock() < quantityRequired) {
			throw new ProductException("Out of stock");
		}
		
		return ProductDto.convertToDto(productOpt.get());
		
	}

	@Override
	@Transactional
	public String modifyProductQuantity(Long productId, Integer quantity, String mode) throws ProductException {
		Optional<Inventory> inventOpt = inventoryRepository.findByProductId(productId);
		Inventory inventory = inventOpt.orElseThrow(()-> new ProductException("No inventory with given ID"));
		
		switch(mode) {
			case "DEC" -> {
				inventory.setQuantityInStock(inventory.getQuantityInStock() - quantity);
				inventory.setQuantitySold(inventory.getQuantitySold() + quantity);

			}
		}
		
		inventoryRepository.save(inventory);
		return "success";
	}
	
	
	
	
}
