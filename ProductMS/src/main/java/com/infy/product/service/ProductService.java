package com.infy.product.service;

<<<<<<< HEAD


import com.infy.product.dto.ProductDto;
=======
import java.util.List;

import com.infy.product.dto.InventoryDto;
import com.infy.product.dto.ProductDto;
import com.infy.product.entity.Product;
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0
import com.infy.product.exception.ProductException;

public interface ProductService {

<<<<<<< HEAD
	ProductDto isProductAvailable(Long productId, Integer quantityRequired) throws ProductException;

	String modifyProductQuantity(Long productId, Integer quantity, String mode) throws ProductException;

=======
	public Product isProductAvailable(Long productId, Integer quantityRequired) throws ProductException;
	public String addProduct(ProductDto productDTO) throws ProductException;
	public ProductDto getProduct(Long productId) throws ProductException;
	public List<ProductDto> getAllProducts();
	public InventoryDto getInventory(Long productId) throws ProductException;
	public InventoryDto updateInventory(Long productId, InventoryDto updateDTO) throws ProductException;
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0
}
