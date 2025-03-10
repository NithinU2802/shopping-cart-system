package com.infy.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.order.dto.CartDto;
import com.infy.order.entity.OrderItem;
import com.infy.order.exception.CartException;
import com.infy.order.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	// Add cart to the customer
	@PostMapping("/{customerId}")
    public ResponseEntity<String> createCart(@PathVariable Long customerId) throws CartException {
        String res = cartService.createCart(customerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	// Add item to the cart
	@PostMapping("/{customerId}/item")
    public ResponseEntity<String> addItem(@PathVariable Long customerId,
                                        @RequestBody OrderItem orderItem) throws CartException {
        String response = cartService.addItem(customerId, orderItem.getProductId(), orderItem.getQuantity());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	// Delete Cart Item
	@DeleteMapping("/{customerId}/items/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable Long customerId, @PathVariable Long itemId) throws CartException{
        String response = cartService.removeItem(customerId, itemId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/{customerId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long customerId) throws CartException{
		CartDto cartDto = cartService.getCart(customerId);
        if (cartDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
	
	
}
