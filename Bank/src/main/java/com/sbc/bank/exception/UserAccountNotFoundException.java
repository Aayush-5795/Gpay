package com.sbc.bank.exception;

public class UserAccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAccountNotFoundException(String message) {
		super(message);
	}
}
