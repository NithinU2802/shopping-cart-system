package com.infy.product.dto;

import java.time.LocalDateTime;

import com.infy.product.entity.Product;

import lombok.Data;

@Data
public class ProductDto {

	Long id;
	
	String name;
	String category;
	String manufacturer;
	String useCase;
	Double price;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
	public Product convertToProduct() {
		Product product = new Product();
		product.setCategory(this.category);
		product.setCreatedAt(this.createdAt);
		product.setId(this.id);
		product.setManufacturer(this.manufacturer);
		product.setName(this.name);
		product.setPrice(this.price);
		product.setUpdatedAt(this.updatedAt);
		product.setUseCase(this.useCase);
		
		return product;
	}
	
	
	public static ProductDto convertToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setCategory(product.getCategory());
		productDto.setCreatedAt(product.getCreatedAt());
		productDto.setId(product.getId());
		productDto.setManufacturer(product.getManufacturer());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setUpdatedAt(product.getUpdatedAt());
		productDto.setUseCase(product.getUseCase());
		
		return productDto;
	}
}
