package com.sbc.gpay;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sbc.gpay.controller.RegistrationController;

@SpringBootTest
class GPayApplicationTests {
	
	@Autowired
	RegistrationController registrationController;

	@Test
	void contextLoads() {
		assertThat(registrationController).isNotNull();
	}

}
