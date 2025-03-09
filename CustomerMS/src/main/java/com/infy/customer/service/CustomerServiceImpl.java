package com.infy.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Boolean isValidCustomer(Long customerId) throws CustomerException {
		Optional<Customer> customerFromDB = customerRepository.findById(customerId);
		if(customerFromDB.isEmpty()) {
			throw new CustomerException("No Customer Found with given ID");
		}
		return true;
	}
}
