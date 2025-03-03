package com.infy.billing.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.billing.dto.BillingDto;

public interface BillingRepository extends CrudRepository<BillingDto, Integer> {

}
