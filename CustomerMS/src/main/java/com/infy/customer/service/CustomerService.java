package com.infy.customer.service;

<<<<<<< HEAD
import com.infy.customer.exception.CustomerException;

public interface CustomerService {

=======
import java.util.List;

import com.infy.customer.dto.CustomerDto;
import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;

public interface CustomerService {
	
	List<CustomerDto> getAllCustomer() throws CustomerException;
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0
	Boolean isValidCustomer(Long customerId) throws CustomerException;
	public String createCustomer(CustomerDto customerDto);
	public CustomerDto getCustomerById(Long customerId) throws CustomerException;

}
