package com.infy.order.service;

import org.springframework.stereotype.Service;

import com.infy.order.repository.CouponRepository;
import com.infy.order.repository.CustomerordersRepository;
import com.infy.order.repository.OrderitemsRepository;

@Service
public class OrderServiceImpl implements OrderService {

	OrderitemsRepository orderitemsRepository;
	CustomerordersRepository customerordersRepository;
	CouponRepository couponRepository;
	
	public OrderServiceImpl(OrderitemsRepository orderitemsRepository, CustomerordersRepository customerordersRepository, CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
		this.customerordersRepository = customerordersRepository;
		this.orderitemsRepository = orderitemsRepository;
	}
}


