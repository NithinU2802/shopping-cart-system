package com.infy.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.billing.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	BillingService billingService;
	
	public BillingController(BillingService billingService) {
		this.billingService = billingService;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
}
