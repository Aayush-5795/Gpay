package com.sbc.gpay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.gpay.dto.StatementResponseDto;
import com.sbc.gpay.service.StatementService;

@RestController
@RequestMapping("/users")
public class StatementController {
	
	private static final Logger logger = LoggerFactory.getLogger(StatementController.class);
	
	@Autowired
	StatementService statementService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<StatementResponseDto> getStatement(@PathVariable long userId) {
		logger.info("Inside getStatement() method");
		StatementResponseDto response=statementService.getStatement(userId);
		logger.info("Exiting getStatement() method");
		return new ResponseEntity<StatementResponseDto>(response, HttpStatus.OK);
	}

}
