package com.sbc.gpay.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.gpay.dto.StatementResponseDto;
import com.sbc.gpay.service.StatementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class StatementController {
	
	@Autowired
	StatementService statementService;
	
	@GetMapping("/{userId}/transactions")
	public ResponseEntity<StatementResponseDto> getStatement(@PathVariable("userId") @NotEmpty @Positive long userId) {
		long startTime=System.currentTimeMillis();
		log.info("Inside getStatement() method");
		StatementResponseDto response=statementService.getStatement(userId);
		log.info("Exiting getStatement() method");
		long endTime=System.currentTimeMillis();
        log.info("Total Time taken in MiliSeconds for StatementController.getStatement()", endTime -startTime);
        return new ResponseEntity<StatementResponseDto>(response, HttpStatus.OK);
	}

}
