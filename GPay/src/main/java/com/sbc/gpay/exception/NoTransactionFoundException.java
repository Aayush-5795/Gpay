package com.sbc.gpay.exception;

public class NoTransactionFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoTransactionFoundException(String message) {
		super(message);
	}
}
