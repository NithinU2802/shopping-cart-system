package com.infy.order.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.infy.order.exception.OrderException;
import com.infy.order.service.OrderService;
import com.infy.order.utility.Cart;
import com.infy.order.utility.SelectedItem;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	OrderService orderService;
	WebClient.Builder webClientBuilder;
	
	public OrderController(OrderService orderService, WebClient.Builder webClientBuilder){
		this.orderService = orderService;
		this.webClientBuilder = webClientBuilder;
	}

	@GetMapping("/")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@PostMapping("/createCart")
	public ResponseEntity<String> createCart(@RequestBody Cart cart) throws OrderException{
		
		//check if customer id is valid
		Boolean isCustomerValid = webClientBuilder.build().get().uri("http://localhost:8200/customer/valid/"+cart.getCustomerId()).retrieve().bodyToMono(Boolean.class).block();
		if(!isCustomerValid) {
			throw new OrderException("Customer Id is Invalid");
		}
		
		for(SelectedItem item: cart.getSelectedItems()) {
			//check if item is valid and required quantity is available
			Boolean isProductAvailable = webClientBuilder.build().get().uri("http://localhost:8200/product/verify/"+item.getProductId()+"?quantityRequired="+item.getQuantity()).retrieve().bodyToMono(Boolean.class).block();
			if(!isProductAvailable) {
				throw new OrderException("Invalid Quantity");
			}
		}
		
		//create a order
		
		//create cart for that product
		// update the quantities in product
		
		return null;
	}
}
