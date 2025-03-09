package com.infy.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.product.dto.ProductDto;

import com.infy.product.exception.ProductException;
import com.infy.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
//	@GetMapping("/verify/{productId}")
//	public ResponseEntity<Boolean> isProductAvailable(@PathVariable Long productId,@RequestParam Integer quantityRequired) throws ProductException{
//		
//		return new ResponseEntity<>(productService.isProductAvailable(productId,quantityRequired),HttpStatus.OK);
//	}
	
	@GetMapping("/verify/{productId}")
	public ResponseEntity<ProductDto> isProductAvailable(@PathVariable Long productId,@RequestParam Integer quantityRequired) throws ProductException{
		
		return new ResponseEntity<>(productService.isProductAvailable(productId,quantityRequired),HttpStatus.OK);
	}
	
//	@GetMapping("/get/{productId}")
//	public ResponseEntity<Boolean> isProductById(@PathVariable Long productId) throws ProductException{
//		
//		return new ResponseEntity<>(productService.isProductAvailable(productId,quantityRequired),HttpStatus.OK);
//	}
	
	
	@PutMapping("/{mode}/{productId}")
	public ResponseEntity<String> modifyProductQuantity(@PathVariable Long productId, @RequestParam Integer quantity, @PathVariable String mode) throws ProductException{
		return new ResponseEntity<>(productService.modifyProductQuantity(productId,quantity,mode), HttpStatus.OK);
	}
}
