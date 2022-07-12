package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
	}
	
	@ExceptionHandler(value = EmployeeAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
		
		return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
	}
	
	@ExceptionHandler(value = NoEmployeeExists.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleNoEmployeeExists(NoEmployeeExists ex) {
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
	}
	
	

}
