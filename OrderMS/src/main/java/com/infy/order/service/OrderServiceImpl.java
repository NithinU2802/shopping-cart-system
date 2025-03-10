package com.infy.order.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

<<<<<<< HEAD
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
=======
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0

@Service
public class OrderServiceImpl implements OrderService {

//	@Autowired
//	private OrderitemRepository orderitemsRepository;
//	@Autowired
//	private OrderRepository customerordersRepository;
//	@Autowired
//	private CouponRepository couponRepository;
	
	
<<<<<<< HEAD
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
=======
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0
}


