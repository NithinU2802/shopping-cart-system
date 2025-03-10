package com.infy.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infy.order.entity.Orderitems;

@Repository
public interface OrderitemsRepository extends JpaRepository<Orderitems, Integer>{

	@Query("SELECT I.productId, I.quantity from Orderitems I where I.orderId = ?1")
	List<Object[]> getProductDetailsByOrderId(Integer orderId);

}
