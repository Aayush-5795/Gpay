package com.sbc.bank.dto;

import java.util.List;

public class StatementResponseDto {
	
	private long accountNumber;
	
	private List<TransactionDto> transactions;

	public List<TransactionDto> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDto> transactions) {
		this.transactions = transactions;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	

}
