package com.sbc.gpay.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sbc.gpay.dto.StatementResponseDto;
import com.sbc.gpay.dto.TransactionDto;
import com.sbc.gpay.service.StatementService;
@SpringBootTest
class StatementControllerTest {
	
	@Mock
	StatementService statementService;
	
	@InjectMocks
	StatementController statementController;
	
	private static long userId=1;
	
	private static List<TransactionDto> getTransactions(){
		List<TransactionDto> list=new ArrayList<TransactionDto>();
		TransactionDto tra1=new TransactionDto(1, "9975147897", "9975147899", 200, "Fund Transfer", LocalDateTime.now());
		list.add(tra1);
		return list;
	}

	@Test
	void testGetStatement() {
		
		List<TransactionDto> list=StatementControllerTest.getTransactions();
		StatementResponseDto response=new StatementResponseDto();
		response.setTransactionList(list);
		when(statementService.getStatement(userId)).thenReturn(response);
		
		StatementResponseDto actual=statementService.getStatement(userId);
		
		assertEquals(response, actual);
		
		ResponseEntity<StatementResponseDto> actuals=statementController.getStatement(userId);
		
		assertEquals(response, actuals.getBody());
		
	}

}
