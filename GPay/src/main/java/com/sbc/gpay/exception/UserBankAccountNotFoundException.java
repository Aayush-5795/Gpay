package com.sbc.gpay.exception;

public class UserBankAccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserBankAccountNotFoundException(String message) {
		super(message);
	}

}
