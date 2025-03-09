package com.infy.product.service;

import java.util.List;

import com.infy.product.dto.InventoryDto;
import com.infy.product.dto.ProductDto;
import com.infy.product.entity.Product;
import com.infy.product.exception.ProductException;

public interface ProductService {

	public Product isProductAvailable(Long productId, Integer quantityRequired) throws ProductException;
	public String addProduct(ProductDto productDTO) throws ProductException;
	public ProductDto getProduct(Long productId) throws ProductException;
	public List<ProductDto> getAllProducts();
	public InventoryDto getInventory(Long productId) throws ProductException;
	public InventoryDto updateInventory(Long productId, InventoryDto updateDTO) throws ProductException;
}
