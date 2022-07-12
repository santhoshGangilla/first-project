package com.example.demo.exception;

public class ErrorResponse {

	private Integer statusCode;
	
	private String errorMessage;

	public ErrorResponse() {
		
	}

	public ErrorResponse(Integer statusCode, String errorMessage) {
		
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
