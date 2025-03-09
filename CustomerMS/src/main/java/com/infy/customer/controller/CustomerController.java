package com.infy.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.customer.exception.CustomerException;
import com.infy.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@GetMapping("/valid/{customerId}")
	public ResponseEntity<Boolean> isValidCustomer(@PathVariable Long customerId) throws CustomerException{
		return new ResponseEntity<>(customerService.isValidCustomer(customerId),HttpStatus.OK);
	}

}
