package com.infy.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.customer.dto.CustomerDto;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customerDto) {
		System.out.print(customerDto);
        String status = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
	
	@GetMapping("/")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CustomerDto>> getAllCustomer() throws CustomerException{
		return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) throws CustomerException, InterruptedException {
		long startTime = System.currentTimeMillis();
        CustomerDto response = customerService.getCustomerById(customerId);
        long endTime = System.currentTimeMillis();
        System.out.println("Customer Id "+customerId+" takes "+((endTime-startTime)/1000)+" to complete the process");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/valid/{customerId}")
	public ResponseEntity<Boolean> isValidCustomer(@PathVariable Long customerId) throws CustomerException{
		return new ResponseEntity<>(customerService.isValidCustomer(customerId),HttpStatus.OK);
	}

}
