package com.infy.customer.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.customer.exception.CustomerException;

import lombok.extern.log4j.Log4j2;



@Aspect
@Component
@Log4j2
public class LoggingAspect {
	
	//private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "(execution(* com.infy.customer.service.*Impl.*(..)))", throwing="exception")
	public void logException(CustomerException exception) {
		log.error(exception.getMessage(), exception);
	}
}
