package com.sbc.gpay.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sbc.gpay.dto.RegisterRequestDto;
import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.service.RegistrationService;
@SpringBootTest
class RegistrationControllerTest {
	
	@Mock
	RegistrationService registrationService;
	
	@InjectMocks
	RegistrationController registrationController;
	
	private static RegisterRequestDto getRegisterDto() {
		RegisterRequestDto register=new RegisterRequestDto();
		register.setFirstName("Aayush");
		register.setLastName("Pandit");
		register.setPassword("Aayush");
		register.setPhone("9975147898");
		register.setSalary(20000);
		register.setUserName("Aayu123");
		register.setAge(25);
		return register;
	}
	
	@Test
	void registerUserTest() {
		ResponseDto expctdresponse=new ResponseDto();
		expctdresponse.setStatusCode(200);
		expctdresponse.setStatusMessage("User created Successfully");
		RegisterRequestDto register=RegistrationControllerTest.getRegisterDto();
		when(registrationService.registerUser(register)).thenReturn(expctdresponse);
		
		ResponseDto actualresponse=registrationService.registerUser(register);
		actualresponse.setStatusCode(200);
		
		assertEquals(expctdresponse.getStatusCode(), actualresponse.getStatusCode());
		
		ResponseEntity<ResponseDto> response=registrationController.registerUser(register);
		
		assertEquals(expctdresponse, response.getBody());
	}

}
