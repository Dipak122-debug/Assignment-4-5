package com.JPA_Example.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(value=CustomerNotFoundException.class)
public ResponseEntity<String> handlingException(CustomerNotFoundException exception){
	
	return new ResponseEntity<String>("Customer Not Found!!",HttpStatus.NOT_FOUND);
}
	
}
