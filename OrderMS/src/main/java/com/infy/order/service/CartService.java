package com.infy.order.service;


import com.infy.order.dto.CartDto;
import com.infy.order.exception.CartException;

public interface CartService {
	public String createCart(Long customerId) throws CartException;
	public String addItem(Long customerId,Long productId,int quantity) throws CartException;
	public String removeItem(Long customerId, Long cartItemId) throws CartException;
	public CartDto getCart(Long customerId) throws CartException;
}
