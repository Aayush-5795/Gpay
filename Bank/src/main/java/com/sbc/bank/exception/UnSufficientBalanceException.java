package com.sbc.bank.exception;

public class UnSufficientBalanceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnSufficientBalanceException(String message) {
		super(message);
	}

}
