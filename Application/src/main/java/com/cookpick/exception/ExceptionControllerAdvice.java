package com.cookpick.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cookpick.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(NoSuchProductException.class)
	public ResponseEntity<ErrorMessage> handleNoCustException(NoSuchProductException ex){
		ErrorMessage errMsg = new ErrorMessage();
		errMsg.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errMsg.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errMsg, HttpStatus.OK);		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleGenericException(Exception ex){
		ErrorMessage errMsg = new ErrorMessage();
		errMsg.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errMsg.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errMsg, HttpStatus.OK);
	}
	
	//Handler for validation failures w.r.t DTOs
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
		
		ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getBindingResult().getAllErrors()
	    		 		  	.stream().map(ObjectError::getDefaultMessage)//lambda equivalent -> x->x.getDefaultMessage()
	    		 		  	.collect(Collectors.joining(", ")));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);	
	}
	
	//Handler for validation failures w.r.t URI parameters
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstarintViolationException(ConstraintViolationException ex){
		
		ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
        		.collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);		
	}
}
