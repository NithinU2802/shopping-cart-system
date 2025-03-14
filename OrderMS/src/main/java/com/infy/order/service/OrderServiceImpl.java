package com.infy.order.service;



import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.infy.order.dto.CustomerordersDto;
import com.infy.order.entity.Coupon;
import com.infy.order.entity.Customerorders;
import com.infy.order.entity.Orderitems;
import com.infy.order.exception.OrderException;
import com.infy.order.repository.CouponRepository;
import com.infy.order.repository.CustomerordersRepository;
import com.infy.order.repository.OrderitemsRepository;
import com.infy.order.utility.NewBillUtil;
import com.infy.order.utility.Order;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	private static Integer counter = 0;
	
	@Override
	public Integer getCounter() {
		try {
	        // Simulate a delay to allow time for concurrent access
	        Thread.sleep(100); // 100ms delay
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		
		counter = counter + 1;
		return counter;
	}
	

	OrderitemsRepository orderitemsRepository;
	CustomerordersRepository customerordersRepository;
	CouponRepository couponRepository;
	
	public OrderServiceImpl(OrderitemsRepository orderitemsRepository, CustomerordersRepository customerordersRepository, CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
		this.customerordersRepository = customerordersRepository;
		this.orderitemsRepository = orderitemsRepository;
	}

	@Override
	public Integer newCart(Customerorders order) throws OrderException {
		Customerorders newOrder =  customerordersRepository.save(order);
		return newOrder.getOrderId();
	}

	@Override
	public Integer newItem(Orderitems orderItem) throws OrderException {
		Orderitems newItem = orderitemsRepository.save(orderItem);
		return newItem.getOrderItemId();
	}

	@Override
	@Transactional
	public NewBillUtil newOrder(Order order) throws OrderException {
		Coupon coupon = null;
		if(order.getCoupon() != null) {
			Optional<Coupon> couponOpt = couponRepository.findByCode(order.getCoupon());
			coupon = couponOpt.orElseThrow(()-> new OrderException("Invalid Coupon"));
		}
		
		
		
		//update order status from cart to placed
		Optional<Customerorders> customerOrderOpt = customerordersRepository.findCustomerCart(order.getCustomerId());
		Customerorders customerOrder = customerOrderOpt.orElseThrow(()-> new OrderException("Cart does not Exists"));
		customerOrder.setOrderStatus("placed");
		
		//place bill
		Double discountAmount = (coupon.getDiscountPercentage()/100) * customerOrder.getTotalAmount();
		Double finalAmount = customerOrder.getTotalAmount() - discountAmount;
		
		NewBillUtil newBill = new NewBillUtil();
		newBill.setCouponId(coupon.getId());
		newBill.setFinalAmount(finalAmount);
		newBill.setOrderId(customerOrder.getOrderId());
		newBill.setPaymentMethod("DB");
		newBill.setPaymentStatus("PENDING");
		
		
		
		return newBill;
	}

	@Override
	public List<Object[]> getProductsByOrderId(Integer orderId) throws OrderException {
		return orderitemsRepository.getProductDetailsByOrderId(orderId);
	}

	@Override
	public Double daySaleValue() throws OrderException {
		Double totalSale =  customerordersRepository.totalDailySale();
		if(totalSale == null) {
			return 0.0;
		}
		return totalSale;
	}

	@Override
	public Double getSales(Integer timeInMinutes) throws OrderException {
		Double sales = customerordersRepository.getSalesForMinutes(timeInMinutes);
		if(sales == null) {
			return 0.0;
		}
		return sales;
	}

	@Override
    public String getTopCustomerByOrderValue(int minutes) throws OrderException{
		
		LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minus(minutes, ChronoUnit.MINUTES);

        List<Customerorders> customerOrders = customerordersRepository.findByOrderDateBetween(startTime, endTime);

        Map<Long, Double> customerOrderTotalMap = new HashMap<>();
        
        for (Customerorders order : customerOrders)
        	customerOrderTotalMap.put(
        		    order.getCustomerId(),
        		    customerOrderTotalMap.getOrDefault(order.getCustomerId(), 0.0) + order.getTotalAmount()
        		);
        
        double max=-1;
        long customerId=0;
        
        for (Map.Entry<Long, Double> entry : customerOrderTotalMap.entrySet()) {
        	if(max<entry.getValue()) {
        		customerId=entry.getKey();
        		max=entry.getValue();
        	}
        }
        
        if(max==-1)
        	throw new OrderException("No order found in the give time period range");
        
        return "Customer "+customerId+" purchased with maximum order value of "+max+" in last "+minutes+" minutes";
    }

	@Override
	public boolean checkAvailability(long customerId) {
		Optional<Customerorders> optional = customerordersRepository.findByCustomerId(customerId);
		if(!optional.isPresent())
			return false;
		return true;
	}
}


