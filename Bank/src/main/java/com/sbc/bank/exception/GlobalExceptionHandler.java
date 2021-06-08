package com.sbc.bank.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserCreationException.class)
	public ResponseEntity<ResponsesStatus> handleUserCreationException(UserCreationException details){
		ResponsesStatus response=new ResponsesStatus();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponsesStatus>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AccountCreationException.class)
	public ResponseEntity<ResponsesStatus> handleAccountCreationException(AccountCreationException details){
		ResponsesStatus response=new ResponsesStatus();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponsesStatus>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(TransactionCreationException.class)
	public ResponseEntity<ResponsesStatus> handleTransactionCreationException(TransactionCreationException details){
		ResponsesStatus response=new ResponsesStatus();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponsesStatus>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UpdateBalanceException.class)
	public ResponseEntity<ResponsesStatus> handleUpdateBalanceException(UpdateBalanceException details){
		ResponsesStatus response=new ResponsesStatus();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponsesStatus>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponsesStatus> handleUserNotFoundException(UserNotFoundException details){
		ResponsesStatus response=new ResponsesStatus();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponsesStatus>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAccountNotFoundException.class)
	public ResponseEntity<ResponsesStatus> handleUserAccountNotFoundException(UserAccountNotFoundException details){
		ResponsesStatus response=new ResponsesStatus();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponsesStatus>(response, HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});

		return errors;
	}


}
