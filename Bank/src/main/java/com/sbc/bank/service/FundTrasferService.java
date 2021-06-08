package com.sbc.bank.service;

import com.sbc.bank.dto.ResponseDto;

public interface FundTrasferService {

	ResponseDto fundTransfer(long fromAccount, long toAccount, double amount);

	Boolean checkAmount(String fromPhone, double amount);

	Boolean fundTransferByPhone(String fromPhone, String toPhone, double amount);

}
