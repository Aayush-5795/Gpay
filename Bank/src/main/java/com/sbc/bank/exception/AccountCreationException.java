package com.sbc.bank.exception;

public class AccountCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountCreationException(String message) {
		super(message);
	}

}
