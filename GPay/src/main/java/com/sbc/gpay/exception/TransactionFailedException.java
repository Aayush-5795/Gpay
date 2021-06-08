package com.sbc.gpay.exception;

public class TransactionFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionFailedException(String message) {
		super(message);
	}

}
