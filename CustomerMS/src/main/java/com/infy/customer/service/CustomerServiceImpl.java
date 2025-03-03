package com.infy.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.customer.dto.CustomerDto;
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

	@Override
	public List<CustomerDto> getAllCustomer() throws CustomerException {
	    Iterable<Customer> allCustomer = customerRepository.findAll();
	    
	    if (allCustomer == null || !allCustomer.iterator().hasNext()) {
	        throw new CustomerException("No customers found");
	    }

	    List<CustomerDto> customerDtoList = new ArrayList<>();
	    for (Customer customer : allCustomer) {
	        CustomerDto customerDto = new CustomerDto();
	        customerDto.setId(customer.getId());
	        customerDto.setName(customer.getName());
	        customerDto.setEmail(customer.getEmail());
	        customerDto.setAddress(customer.getAddress());
	        customerDto.setPhoneNumber(customer.getPhoneNumber());
	        customerDto.setCreatedAt(customer.getCreatedAt());
	        customerDto.setUpdatedAt(customer.getUpdatedAt());
	        customerDtoList.add(customerDto);
	    }

	    return customerDtoList;
	}

	
	
	
}
