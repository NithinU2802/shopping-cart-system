package com.infy.order.service;

<<<<<<< HEAD
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.infy.order.entity.Customerorders;
import com.infy.order.entity.Orderitems;
import com.infy.order.exception.OrderException;
import com.infy.order.utility.NewBillUtil;
import com.infy.order.utility.Order;
=======
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0

public interface OrderService {


	Integer newItem(Orderitems orderItem) throws OrderException ;

	Integer newCart(Customerorders order) throws OrderException;

	NewBillUtil newOrder(Order order) throws OrderException;

	List<Object[]> getProductsByOrderId(Integer orderId) throws OrderException;

	Double daySaleValue() throws OrderException ;

	Double getSales(Integer timeInMinutes) throws OrderException;

}
