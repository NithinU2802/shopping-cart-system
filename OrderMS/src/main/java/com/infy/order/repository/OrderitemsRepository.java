package com.infy.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.order.entity.Orderitems;

public interface OrderitemsRepository extends CrudRepository<Orderitems, Integer>{

	@Query("SELECT I.productId, I.quantity from Orderitems I where I.orderId = ?1")
	List<Object[]> getProductDetailsByOrderId(Integer orderId);

}
