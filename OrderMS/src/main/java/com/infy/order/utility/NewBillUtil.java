package com.infy.order.utility;

import lombok.Data;

@Data
public class NewBillUtil {
	
	Integer orderId;
	Long couponId;
	Double finalAmount;
	String paymentMethod;
	String paymentStatus;
}
