package com.sample.exception;

import org.springframework.stereotype.Component;

@Component
public class BussinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public BussinessException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public BussinessException() {
		
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
