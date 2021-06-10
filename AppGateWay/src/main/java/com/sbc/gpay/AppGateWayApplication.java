package com.sbc.gpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGateWayApplication.class, args);
	}

}
