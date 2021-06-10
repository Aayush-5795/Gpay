package com.sbc.gpay.service;

import com.sbc.gpay.dto.StatementResponseDto;

public interface StatementService {

	StatementResponseDto getStatement(long userId);

}
