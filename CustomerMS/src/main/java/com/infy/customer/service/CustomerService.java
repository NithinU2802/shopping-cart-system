package com.infy.customer.service;

import java.util.List;

import com.infy.customer.dto.CustomerDto;
import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;

public interface CustomerService {
	
	List<CustomerDto> getAllCustomer() throws CustomerException;
	Boolean isValidCustomer(Long customerId) throws CustomerException;
	public String createCustomer(CustomerDto customerDto);
	public CustomerDto getCustomerById(Long customerId) throws CustomerException, InterruptedException;

}
