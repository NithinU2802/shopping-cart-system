package com.infy.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.billing.repository.BillingRepository;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	BillingRepository billingRepository;
	
	public BillingServiceImpl(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}
}

