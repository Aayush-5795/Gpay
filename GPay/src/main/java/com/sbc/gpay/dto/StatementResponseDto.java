package com.sbc.gpay.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StatementResponseDto {
	
	private List<TransactionDto> transactionList;

}
