package com.infy.order.controller;


import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import com.infy.order.dto.ProductDto;
import com.infy.order.entity.Customerorders;
import com.infy.order.entity.Orderitems;
import com.infy.order.exception.OrderException;
import com.infy.order.service.OrderService;

import com.infy.order.utility.Cart;
import com.infy.order.utility.NewBillUtil;
import com.infy.order.utility.Order;
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

	// @GetMapping("/")
	// public ResponseEntity<String> get(){
	// 	return new ResponseEntity<>("Success",HttpStatus.OK);
	// }

	@GetMapping("/getCounter")
	public ResponseEntity<Integer> getCounter(){
		return new ResponseEntity<>(orderService.getCounter(),HttpStatus.OK);
	}
	
	@PostMapping("/createCart")
	public ResponseEntity<String> createCart(@RequestBody Cart cart) throws OrderException{
		
		//check if customer id is valid
		Boolean isCustomerValid = webClientBuilder.build().get().uri("http://localhost:8200/customer/valid/"+cart.getCustomerId()).retrieve().bodyToMono(Boolean.class).block();
		if(!isCustomerValid) {
			throw new OrderException("Customer Id is Invalid");
		}
		
		List<ProductDto> productDtoList = new ArrayList<>();
		Double totalPrice = 0.0;
		
		for(SelectedItem item: cart.getSelectedItems()) {
			//check if item is valid and required quantity is available
			ProductDto productDto = webClientBuilder.build().get().uri("http://localhost:8100/product/verify/"+item.getProductId()+"?quantityRequired="+item.getQuantity()).retrieve().bodyToMono(ProductDto.class).block();
			productDtoList.add(productDto);
			
//			if(!isProductAvailable) {
//				throw new OrderException("Invalid Quantity");
//			}
			totalPrice = totalPrice + productDto.getPrice();
		}
		
		//create a order
		Customerorders order = new Customerorders();
		order.setCustomerId(cart.getCustomerId());
		order.setTotalAmount(totalPrice);
		order.setOrderStatus("cart");
		order.setOrderDate(LocalDateTime.now());
		
		//save order 
		Integer orderId = orderService.newCart(order);
		
		//updating items in the cart
		int index = 0;
		for(SelectedItem item : cart.getSelectedItems()) {
			Orderitems orderItem = new Orderitems();
			orderItem.setOrderId(orderId);
			orderItem.setPrice(productDtoList.get(index).getPrice() * item.getQuantity());
			orderItem.setProductId(item.getProductId());
			orderItem.setQuantity(item.getQuantity());
			
			//save item
			orderService.newItem(orderItem);
			
			//send productid and quantity to reduce the quant in inventory table
			// this is cart so not required, we can do it while ordering time
			//String result =  webClientBuilder.build().put().uri("http://localhost:8100/product/DEC/"+item.getProductId()+"?quantity="+item.getQuantity()).retrieve().bodyToMono(String.class).block();
			
			index++;
		}
		
		
		
		
		return new ResponseEntity<>("Cart created succesfully", HttpStatus.OK);
	}
	
	
	@PostMapping("/new")
	public ResponseEntity<String> newOrder(@RequestBody Order order) throws OrderException{
		
		//check if customer id is valid
		Boolean isCustomerValid = webClientBuilder.build().get().uri("http://localhost:8200/customer/valid/"+order.getCustomerId()).retrieve().bodyToMono(Boolean.class).block();
		if(!isCustomerValid) {
			throw new OrderException("Customer Id is Invalid");
		}
		
		NewBillUtil newBill = orderService.newOrder(order);
		Integer billingId = webClientBuilder.build().post().uri("http://localhost:9400/billing/new").bodyValue(newBill).retrieve().bodyToMono(Integer.class).block();
		
		
		//update the quantity of those products in products table
		List<Object[]> productDetails = orderService.getProductsByOrderId(newBill.getOrderId());
		for(Object[] product : productDetails) {
			String result =  webClientBuilder.build().put().uri("http://localhost:8100/product/DEC/"+product[0]+"?quantity="+product[1]).retrieve().bodyToMono(String.class).block();
		}
		
		
		String responseMessage = "Order succesfully placed with billing id:"+ billingId;
		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
	}
	
	@GetMapping("/daysale")
	public ResponseEntity<Double> daySaleValue() throws OrderException{
		return new ResponseEntity<>(orderService.daySaleValue(), HttpStatus.OK);
	}
	
	@GetMapping("/getsales/{timeInMinutes}")
	public ResponseEntity<Double> getSales(@PathVariable Integer timeInMinutes) throws OrderException{
		return new ResponseEntity<>(orderService.getSales(timeInMinutes), HttpStatus.OK);
	}
	
}
