package com.sbc.bank.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.bank.dto.RegisterRequestDto;
import com.sbc.bank.dto.ResponseDto;
import com.sbc.bank.service.RegisterService;

@RestController
@RequestMapping("/users")
@Validated
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	RegisterService registerService;
	
	@PostMapping("/registerUser")
	public ResponseDto registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
		logger.info("Inside Controller registerUser() method");
		return registerService.registerUser(registerRequestDto);		
	}
	
	@GetMapping("/checkPhone")
	public Boolean checkPhone(@RequestParam String phone) {
		logger.info("Inside Controller checkPhone() method");
		return registerService.checkPone(phone);
	}

}
