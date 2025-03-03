package com.infy.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.customer.entity.Customer;

public interface CustomerRepository  extends CrudRepository<Customer, Long>{

}
