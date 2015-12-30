package com.exceptions;

public class InvalidParameterException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public InvalidParameterException(String message) {
		this.message = message;
	}
}
