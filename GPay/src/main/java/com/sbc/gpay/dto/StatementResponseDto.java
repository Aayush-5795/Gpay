package com.sbc.gpay.dto;

import java.util.List;

public class StatementResponseDto {
	
	private List<TransactionDto> transactionList;

	public List<TransactionDto> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionDto> transactionList) {
		this.transactionList = transactionList;
	}

	
	

}
