package com.infy.billing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.billing.dto.BillingDto;
import com.infy.billing.exception.BillingException;
import com.infy.billing.service.BillingService;
import com.infy.billing.utility.NewBillUtil;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	BillingService billingService;
	
	public BillingController(BillingService billingService) {
		this.billingService = billingService;
	}
	
//	@GetMapping("/")
//	public ResponseEntity<String> get(){
//		return new ResponseEntity<>("Success",HttpStatus.OK);
//	}
	
	@PostMapping("/new")
	public ResponseEntity<Integer> newBill(@RequestBody NewBillUtil newBill) throws BillingException{
		//verify order id
		return new ResponseEntity<>(billingService.newBill(newBill), HttpStatus.CREATED);
		
	}
}
