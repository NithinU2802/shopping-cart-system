package com.infy.billing.service;

import org.springframework.stereotype.Service;

import com.infy.billing.dto.BillingDto;
import com.infy.billing.entity.Billing;
import com.infy.billing.exception.BillingException;
import com.infy.billing.repository.BillingRepository;
import com.infy.billing.utility.NewBillUtil;

@Service
public class BillingServiceImpl implements BillingService {

	BillingRepository billingRepository;
	
	public BillingServiceImpl(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}

	@Override
	public Integer newBill(NewBillUtil newBill) throws BillingException {
		Billing billing = new Billing();
		
		billing.setOrderId(newBill.getOrderId());
		billing.setCouponId(newBill.getCouponId());
		billing.setFinalAmount(newBill.getFinalAmount());
		billing.setPaymentMethod(newBill.getPaymentMethod());
		billing.setPaymentStatus(newBill.getPaymentStatus());
		
		return billingRepository.save(billing).getBillingId();
	}
}

