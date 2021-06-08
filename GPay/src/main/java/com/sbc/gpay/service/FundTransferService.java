package com.sbc.gpay.service;

import com.sbc.gpay.dto.ResponseDto;

public interface FundTransferService {

	ResponseDto fundTransferByPhone(String fromPhone, String toPhone, double amount);

}
