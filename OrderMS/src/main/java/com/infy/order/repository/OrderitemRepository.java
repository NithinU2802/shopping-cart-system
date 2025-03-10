package com.infy.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.order.entity.OrderItem;

@Repository
public interface OrderitemRepository extends CrudRepository<OrderItem, Integer>{

}
