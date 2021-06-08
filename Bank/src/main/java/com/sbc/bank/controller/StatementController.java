package com.sbc.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.bank.dto.StatementResponseDto;
import com.sbc.bank.service.StatementService;

@RestController
@RequestMapping("/users")
public class StatementController {
	
	private static final Logger logger = LoggerFactory.getLogger(StatementController.class);
	
	@Autowired
	StatementService statementService;
	
	@GetMapping("/bankStatement")
	public StatementResponseDto getStatement(@RequestParam long accountNumber) {
		logger.info("Inside Controller getStatement() method");
		return statementService.getStatement(accountNumber);
	}

}
