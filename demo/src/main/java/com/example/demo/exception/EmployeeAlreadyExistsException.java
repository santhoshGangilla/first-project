package com.example.demo.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{

	private String exceptionMessage;

	
	public EmployeeAlreadyExistsException() {
		
	}


	public EmployeeAlreadyExistsException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}
	
	
}
