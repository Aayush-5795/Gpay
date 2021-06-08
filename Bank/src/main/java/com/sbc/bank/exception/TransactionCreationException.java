package com.sbc.bank.exception;

public class TransactionCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionCreationException(String message) {
		super(message);
	}

}
