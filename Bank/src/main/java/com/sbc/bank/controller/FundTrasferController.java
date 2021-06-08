package com.sbc.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.bank.dto.ResponseDto;
import com.sbc.bank.service.FundTrasferService;

@RestController
@RequestMapping("/users")
public class FundTrasferController {
	
	private static final Logger logger = LoggerFactory.getLogger(FundTrasferController.class);
	
	@Autowired
	FundTrasferService fundTrasferService;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/fundTransfer")
	public ResponseDto fundTransfer(@RequestParam long fromAccount,@RequestParam long toAccount,@RequestParam double amount) {
		logger.info("Inside controller fundTransfer() method..");
		return fundTrasferService.fundTransfer(fromAccount,toAccount,amount);
	}
	
	@PostMapping("/fundTransferByPhone")
	public Boolean fundTransferByPhone(@RequestParam String fromPhone,@RequestParam String toPhone,@RequestParam double amount) {
		logger.info("Inside controller fundTransferByPhone() method..");
		return fundTrasferService.fundTransferByPhone(fromPhone,toPhone,amount);
	}

	
	@GetMapping("/port")
	public String getPortNo() {
		logger.info("Inside controller getPortNo() method..");
		String port = environment.getProperty("local.server.port");
		return "From Order app : " + port;
	}
	
	@GetMapping("/checkAmount")
	public Boolean checkAmount(@RequestParam String fromPhone,@RequestParam double amount) {
		logger.info("Inside controller checkAmount() method..");
		return fundTrasferService.checkAmount(fromPhone,amount);
	}

}
