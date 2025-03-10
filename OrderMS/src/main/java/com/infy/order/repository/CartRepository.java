package com.infy.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.order.entity.Cart;

import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerId(Long customerId);
}

