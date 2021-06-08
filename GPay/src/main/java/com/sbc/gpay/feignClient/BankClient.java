package com.sbc.gpay.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient("http://Bank-Service/bank/users")
@FeignClient(value = "bank-service", url = "http://localhost:7777/bank/users")
public interface BankClient {
	
	@GetMapping("/port")
	public String getPortNo();
	
	@GetMapping("/checkPhone")
	public Boolean checkPhone(@RequestParam String phone);
	
	@GetMapping("/checkAmount")
	public Boolean checkAmount(@RequestParam String fromPhone,@RequestParam double amount);
	
	@PostMapping("/fundTransferByPhone")
	public Boolean fundTransferByPhone(@RequestParam String fromPhone,@RequestParam String toPhone,@RequestParam double amount);

}
