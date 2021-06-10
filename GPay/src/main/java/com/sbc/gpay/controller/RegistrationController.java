package com.sbc.gpay.controller;

import javax.validation.Valid;
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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gpay")
@Validated
@Slf4j
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto request) {
		long startTime=System.currentTimeMillis();
		log.info("Inside the registerUser() method");
		ResponseDto response=registrationService.registerUser(request);
		log.info("Exiting the registerUser() method");
		long endTime=System.currentTimeMillis();
        log.info("Total Time taken in MiliSeconds for RegistrationController.registerUser()", endTime -startTime);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.CREATED);
	}

}
