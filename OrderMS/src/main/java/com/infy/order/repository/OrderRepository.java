package com.infy.order.repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.order.entity.CustomerOrder;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Integer> {
//	@Query("SELECT o.customerId, SUM(o.totalAmount) as totalSpent " +
//	           "FROM CustomerOrder o WHERE o.orderDate >= :startDate " +
//	           "GROUP BY o.customerId ORDER BY totalSpent DESC")
//	    List<Object[]> findTopCustomerSince(@Param("startDate") LocalDateTime startDate, Pageable pageable);
}
