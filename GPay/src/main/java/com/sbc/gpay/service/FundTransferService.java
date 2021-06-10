package com.sbc.gpay.service;


public interface FundTransferService {

	void fundTransferByPhone(String fromPhone, String toPhone, double amount);

}
