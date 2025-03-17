package com.infy.order.service;

import java.util.List;

import com.infy.order.dto.CustomerordersDto;
import com.infy.order.entity.Customerorders;
import com.infy.order.entity.Orderitems;
import com.infy.order.exception.OrderException;
import com.infy.order.utility.NewBillUtil;
import com.infy.order.utility.Order;

public interface OrderService {


	Integer newItem(Orderitems orderItem) throws OrderException ;

	Integer newCart(Customerorders order) throws OrderException;

	NewBillUtil newOrder(Order order) throws OrderException;

	List<Object[]> getProductsByOrderId(Integer orderId) throws OrderException;
	
	CustomerordersDto getOrderDetailByOrderId(Integer orderId) throws OrderException, InterruptedException;

	Double daySaleValue() throws OrderException ;

	Double getSales(Integer timeInMinutes) throws OrderException;

	Integer getCounter();
	
	boolean checkAvailability(long customerId);
	
	String getTopCustomerByOrderValue(int timeInMinutes) throws OrderException;

}
