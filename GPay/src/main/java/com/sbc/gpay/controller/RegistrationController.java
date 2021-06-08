package com.sbc.gpay.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.gpay.dto.RegisterRequestDto;
import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.service.RegistrationService;

@RestController
@RequestMapping("/banking")
@Validated
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto request) {
		logger.info("Inside the registerUser() method");
		ResponseDto response=registrationService.registerUser(request);
		logger.info("Exiting the registerUser() method");
		return new ResponseEntity<ResponseDto>(response, HttpStatus.CREATED);
	}

}
