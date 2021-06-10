package com.sbc.gpay.controller;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.gpay.service.FundTransferService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/banking")
@Slf4j
public class FundTransferController {
	
	@Autowired
	FundTransferService fundTransferService;
	
	@PostMapping("/fundTrasferByPhone")
	public void fundTransferByPhone(@RequestParam @NotEmpty String fromPhone,@RequestParam @NotEmpty String toPhone,@RequestParam @Digits(integer = 14, fraction = 3) @Positive double amount) {
		long startTime=System.currentTimeMillis();
		log.info("Inside the fundTransferByPhone(fromPhone,toPhone,amount) method {}{}{} ",fromPhone,toPhone,amount); 
		fundTransferService.fundTransferByPhone(fromPhone,toPhone,amount);
		log.info("Exiting the fundTransferByPhone() method");
		long endTime=System.currentTimeMillis();
       		log.info("Total Time taken in MiliSeconds for FundsTransferController.fundTransferByPhone()", endTime -startTime);
	}
	
	

}
