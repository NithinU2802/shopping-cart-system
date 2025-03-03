package com.infy.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.product.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
