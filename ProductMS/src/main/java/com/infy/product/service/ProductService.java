package com.infy.product.service;



import com.infy.product.dto.ProductDto;
import com.infy.product.exception.ProductException;

public interface ProductService {

	ProductDto isProductAvailable(Long productId, Integer quantityRequired) throws ProductException;

	String modifyProductQuantity(Long productId, Integer quantity, String mode) throws ProductException;

}
