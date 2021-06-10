package com.sbc.gpay.exception;

public class FromAndToPhoneCanNotBeSameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FromAndToPhoneCanNotBeSameException(String message) {
		super(message);
	}
}
