package com.sbc.bank.service;

import com.sbc.bank.dto.StatementResponseDto;

public interface StatementService {

	StatementResponseDto getStatement(long accountNumber);

}
