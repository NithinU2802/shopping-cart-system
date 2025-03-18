package com.infy.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Jetty.Threads;
import org.springframework.stereotype.Service;

import com.infy.customer.dto.CustomerDto;
import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.repository.CustomerRepository;
import com.infy.customer.utility.ThreadDumpUtil;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	private static final Object lock = new Object();
	
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
	
	public String createCustomer(CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
        System.out.println(customer);
        return "Customer Created Successfully";
    }

	@Override
	public List<CustomerDto> getAllCustomer() throws CustomerException {
	    Iterable<Customer> allCustomer = customerRepository.findAll();
	    
        try {
        	
//        	// c) inject a bug by causing an out of memory in the application when dealing with some reports
//        	
//        	List<String> memo = new ArrayList<>();
//            for (int i = 0; i < 100_000_000; i++)
//                memo.add("Record " + i);
//            
//            if (allCustomer == null || !allCustomer.iterator().hasNext()) {
//            	throw new CustomerException("No customers found");
//            }

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
        }catch(OutOfMemoryError e) {
        	throw e;
        }
	}

	@Override
    public CustomerDto getCustomerById(Long customerId) throws CustomerException, InterruptedException{
//		// b) inject a bug in the application to cause a latency due to a synchronized code
//		synchronized(lock) {
//			Thread.sleep(10000);
			Optional<Customer> customerOpt = customerRepository.findById(customerId);
        	if (customerOpt.isPresent()) {
            	return modelMapper.map(customerOpt.get(), CustomerDto.class);
        	} else {
            	throw new CustomerException("Customer not found with id: " + customerId);
        	}
//		}
    }
	
	
	// Make some concurrent transactions for different users in the solution and take thread dumps while doing 
	// those transactions 
	
	private ExecutorService executorService = Executors.newFixedThreadPool(10); 
	
	public String simulateConcurrentTransactions(List<CustomerDto> customerDtos) {
        for (CustomerDto customerDto : customerDtos) {
            executorService.submit(() -> {
                // Simulating a transaction: saving user to the database
                createCustomer(customerDto);

                // Trigger thread dump (this will print thread info to the console)
                triggerThreadDump();
            });
        }
        
        return "Check the console where concurrent transaction has been done";
    }


    // Trigger a thread dump to the console
    private void triggerThreadDump() {
        ThreadDumpUtil.printThreadDump();
    }
	
}
