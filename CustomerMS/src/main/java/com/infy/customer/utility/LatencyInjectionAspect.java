package com.infy.customer.utility;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LatencyInjectionAspect {
	
	//Introducing random delay before db execution
	@Before("execution(* com.infy.customer.repository.CustomerRepository.*(..))")
	public void injectDbLatency() throws InterruptedException{
		
		//random delay 
		Random rand = new Random();
		int delayDuration = rand.nextInt(2000) + 100; //between 100ms and 2s
		System.out.println("Injecting artificial delay of " + delayDuration + "ms...");
		
		TimeUnit.MILLISECONDS.sleep(delayDuration);
		
		
		
		
	}
}
