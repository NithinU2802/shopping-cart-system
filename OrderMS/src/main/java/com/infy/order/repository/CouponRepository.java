package com.infy.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.order.entity.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

	@Query("SELECT C from Coupon C where C.code LIKE ?1")
	Optional<Coupon> findByCode(String coupon);

}
