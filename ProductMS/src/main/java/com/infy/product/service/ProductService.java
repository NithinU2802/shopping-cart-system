package com.infy.product.service;



import com.infy.product.dto.InventoryReplenishmentDTO;
import com.infy.product.dto.ProductDto;
import java.util.List;

import com.infy.product.dto.InventoryDto;
import com.infy.product.exception.ProductException;

public interface ProductService {
	String modifyProductQuantity(Long productId, Integer quantity, String mode) throws ProductException;
	public ProductDto isProductAvailable(Long productId, Integer quantityRequired) throws ProductException;
	public String addProduct(ProductDto productDTO) throws ProductException;
	public ProductDto getProduct(Long productId) throws ProductException;
	public List<ProductDto> getAllProducts();
	public InventoryDto getInventory(Long productId) throws ProductException;
	public InventoryDto updateInventory(Long productId, InventoryDto updateDTO) throws ProductException;

	List<InventoryReplenishmentDTO> getProductsToBeReplenished() throws ProductException;
}
