package com.infy.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.order.entity.Coupon;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {

}
