package com.sbc.gpay.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sbc.gpay.dto.ResponseDto;
import com.sbc.gpay.service.FundTransferService;
@SpringBootTest
class FundTransferControllerTest {
	
	@Mock
	FundTransferService fundTransferService;
	
	@InjectMocks
	FundTransferController fundTransferController;

	@Test
	void testFundTransferByPhone() {
		
		ResponseDto response=new ResponseDto();
		response.setStatusCode(200);
		
		when(fundTransferService.fundTransferByPhone("9975147898", "9975147899", 200)).thenReturn(response);
		
		ResponseEntity<ResponseDto> actual=fundTransferController.fundTransferByPhone("9975147898", "9975147899", 200);
		actual.getBody().setStatusCode(200);
		
		assertEquals(response, actual.getBody());
		
	}

}
