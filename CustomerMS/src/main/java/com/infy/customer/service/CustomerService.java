package com.infy.customer.service;

import com.infy.customer.exception.CustomerException;

public interface CustomerService {

	Boolean isValidCustomer(Long customerId) throws CustomerException;

}
