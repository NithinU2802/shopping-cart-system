package com.infy.customer.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Boolean isValidCustomer(Long customerId) throws CustomerException {
		Optional<Customer> customerFromDB = customerRepository.findById(customerId);
		if(customerFromDB.isEmpty()) {
			throw new CustomerException("No Customer Found with given ID");
		}
		return true;
	}
<<<<<<< HEAD
=======
	
	public String createCustomer(CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
        System.out.println(customer);
        return "Customer Created Successfully";
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

	@Override
    public CustomerDto getCustomerById(Long customerId) throws CustomerException{
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            return modelMapper.map(customerOpt.get(), CustomerDto.class);
        } else {
            throw new CustomerException("Customer not found with id: " + customerId);
        }
    }
	
	
	
>>>>>>> b08376a0ad5be0afbab1165c696d09322964bfe0
}
