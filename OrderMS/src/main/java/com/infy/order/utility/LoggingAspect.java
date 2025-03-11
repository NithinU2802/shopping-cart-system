package com.infy.order.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.order.exception.OrderException;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class LoggingAspect {
	
	@AfterThrowing(pointcut = "(execution(* com.infy.order.service.*Impl.*(..)))", throwing = "exception")
	public void serviceLogMethod(OrderException exception) {
		log.error(exception.getMessage(), exception);
	}
	
	@AfterThrowing(pointcut = "(execution(* com.infy.order.controller.*.*(..)))", throwing = "exception")
	public void controllerLogMethod(Exception exception) {
		log.error(exception.getMessage(), exception);
	}
}
