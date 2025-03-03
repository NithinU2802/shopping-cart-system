package com.infy.customer.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomerDto {
	Long id;
	
	String name;
	String email;
	String phoneNumber;
	String address;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
}
