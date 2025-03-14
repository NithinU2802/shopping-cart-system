package com.infy.order.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.order.entity.Customerorders;

public interface CustomerordersRepository extends CrudRepository<Customerorders, Integer> {

	Optional<Customerorders> findByCustomerId(Long customerId);
	
	List<Customerorders> findByOrderDateBetween(LocalDateTime startTime, LocalDateTime endTime);

	@Query("SELECT SUM(C.totalAmount) from Customerorders C where DATE(C.orderDate) = CURDATE()")
	Double totalDailySale();
	
	@Query(value = "SELECT SUM(C.total_amount) FROM CustomerOrders C WHERE C.order_date >= (NOW() - INTERVAL ?1 MINUTE)", nativeQuery = true)
	Double getSalesForMinutes(Integer timeInMinutes);


	@Query("SELECT C from Customerorders C where C.customerId = ?1 AND C.orderStatus LIKE 'cart'")
	Optional<Customerorders> findCustomerCart(Long customerId);

	

}
