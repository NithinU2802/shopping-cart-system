package com.infy.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.order.entity.Orderitems;

@Repository
public interface OrderitemRepository extends CrudRepository<Orderitems, Integer>{

}
