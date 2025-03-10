package com.infy.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.order.dto.CartDto;
import com.infy.order.dto.ProductDto;
import com.infy.order.entity.Cart;
import com.infy.order.entity.CartItem;
import com.infy.order.exception.CartException;
import com.infy.order.repository.CartItemRepository;
import com.infy.order.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService{
	
	private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductClient productClient;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productClient = productClient;
    }
    
    public String createCart(Long customerId) throws CartException{
    	Cart cart = new Cart();
        cart.setCustomerId(customerId);
        if(cartRepository.save(cart).getCustomerId()==customerId)
        	return "Cart Created Successfully";
        else
        	throw new CartException("Internal Issue Cannot create a Cart");
    }

    @Transactional
    public String addItem(Long customerId, Long productId, int quantity) throws CartException{
        Cart cart = cartRepository.findByCustomerId(customerId).get();
        
        if(cart==null) {
        	createCart(customerId);
        	cart = cartRepository.findByCustomerId(customerId).get();
        }
        
        ProductDto productDto = productClient.getProductById(productId);
        Double unitPrice = productDto.getPrice();
        
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setUnitPrice(unitPrice*quantity);
        
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(cartItem);
        cartItemRepository.save(cartItem);
        
        cart.setUpdatedAt(java.time.LocalDateTime.now());
        cartRepository.save(cart);
        return "Item Added Successfully";
    }
    
    @Transactional
    public String removeItem(Long customerId, Long cartItemId) throws CartException{
    	Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartException("Cart not found for customer: " + customerId));
        
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        if(cartItemRepository.findById(cartItemId)==null)
        	throw new CartException("Item is not available");
        cartItemRepository.deleteById(cartItemId);
        cart.setUpdatedAt(java.time.LocalDateTime.now());
        cartRepository.save(cart);
        return "Item has been removed successfully";
    } 
    
    public CartDto getCart(Long customerId) {
        return modelMapper.map(cartRepository.findByCustomerId(customerId).orElse(null),CartDto.class);
    }
	
}
