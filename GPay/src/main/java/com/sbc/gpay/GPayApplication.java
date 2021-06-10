package com.sbc.gpay;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class GPayApplication {
	
	@Bean
	public ModelMapper mapmodelmapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(GPayApplication.class, args);
	}

}
