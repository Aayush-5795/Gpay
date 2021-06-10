package com.sbc.gpay.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.sbc.gpay.service.FundTransferService;
@SpringBootTest
class FundTransferControllerTest {
	
	@Mock
	FundTransferService fundTransferService;
	
	@InjectMocks
	FundTransferController fundTransferController;

	@Test
	void testFundTransferByPhone() {
		
		fundTransferService.fundTransferByPhone("9975147898", "9975147899", 200);
		fundTransferController.fundTransferByPhone("9975147898", "9975147899", 200);
	}

}
