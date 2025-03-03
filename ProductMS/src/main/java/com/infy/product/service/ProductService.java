package com.infy.product.service;

import com.infy.product.entity.Product;
import com.infy.product.exception.ProductException;

public interface ProductService {

	Product isProductAvailable(Long productId, Integer quantityRequired) throws ProductException;

}
