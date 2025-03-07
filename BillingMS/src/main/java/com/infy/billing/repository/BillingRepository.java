package com.infy.billing.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.billing.entity.Billing;

public interface BillingRepository extends CrudRepository<Billing, Integer> {

}
