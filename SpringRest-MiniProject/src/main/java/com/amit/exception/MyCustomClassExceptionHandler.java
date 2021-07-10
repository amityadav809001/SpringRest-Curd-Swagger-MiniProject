package com.amit.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amit.error.ErrorType;

@RestControllerAdvice
public class MyCustomClassExceptionHandler {

	@ExceptionHandler(EmployeeNotFooundException.class)
	public ResponseEntity<ErrorType> handleEmployeeNotFound(EmployeeNotFooundException enfe) {
		return new ResponseEntity<ErrorType>(
				new ErrorType(new Date().toString(), "Employee", enfe.getMessage(), "Processing Issue"),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
