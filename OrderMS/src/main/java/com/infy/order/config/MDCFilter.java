package com.infy.order.config;

import java.io.IOException;
import java.util.UUID;

import org.jboss.logging.MDC;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;

public class MDCFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			//HttpServletRequest httpRequest = (HttpServletRequest) request;
			String requestId = UUID.randomUUID().toString();
			
			MDC.put("requestId", requestId);
			chain.doFilter(request, response);
			
		}finally {
			MDC.clear(); // clear MDC after request is processed
		}
		
	}

}
