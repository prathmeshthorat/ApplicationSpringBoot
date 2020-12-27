package com.cookpick.exception;

public class NoSuchProductException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchProductException() {
		super();
	}

	public NoSuchProductException(String message) {
		super(message);
	}

	
}
