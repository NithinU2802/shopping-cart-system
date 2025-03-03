package com.infy.report.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@GetMapping("/")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}
}
