package com.sbc.gpay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.service.FundTransferService;

@RestController
@RequestMapping("/banking")
public class FundTransferController {
	
	private static final Logger logger = LoggerFactory.getLogger(FundTransferController.class);
	
	@Autowired
	FundTransferService fundTransferService;
	
	@PostMapping("/fundTrasferByPhone")
	public ResponseEntity<ResponseDto> fundTransferByPhone(@RequestParam String fromPhone,@RequestParam String toPhone,@RequestParam double amount) {
		logger.info("Inside the fundTransferByPhone() method");
		ResponseDto response=fundTransferService.fundTransferByPhone(fromPhone,toPhone,amount);
		logger.info("Exiting the fundTransferByPhone() method");
		return new ResponseEntity<ResponseDto>(response, HttpStatus.ACCEPTED);
	}
	
	

}
