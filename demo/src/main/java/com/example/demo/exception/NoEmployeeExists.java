package com.example.demo.exception;

public class NoEmployeeExists extends RuntimeException{
	
	private String exceptionMessage;

	
	public NoEmployeeExists() {
		
	}


	public NoEmployeeExists(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}
	
	

}
