package com.sbc.gpay.exception;

public class UnsufficientBalanceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsufficientBalanceException(String message) {
		super(message);
	}

}
