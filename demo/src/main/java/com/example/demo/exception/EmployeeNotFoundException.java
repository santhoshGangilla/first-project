package com.example.demo.exception;

public class EmployeeNotFoundException extends RuntimeException{
	
	private String exceptionMessage;

	
	public EmployeeNotFoundException() {
		
	}


	public EmployeeNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}

}
