package com.sbc.gpay.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserBankAccountNotFoundException.class)
	public ResponseEntity<ResponseStatuss> handleUserBankAccountNotFoundException(UserBankAccountNotFoundException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(401);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStatuss> handleUserNotFoundException(UserNotFoundException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyPresentException.class)
	public ResponseEntity<ResponseStatuss> handleUserAlreadyPresentException(UserAlreadyPresentException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(500);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(UnsufficientBalanceException.class)
	public ResponseEntity<ResponseStatuss> handleUnsufficientBalanceException(UnsufficientBalanceException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(402);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotCreatedException.class)
	public ResponseEntity<ResponseStatuss> handleUserNotCreatedException(UserNotCreatedException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(502);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(TransactionFailedException.class)
	public ResponseEntity<ResponseStatuss> handleTransactionFailedException(TransactionFailedException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(502);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoTransactionFoundException.class)
	public ResponseEntity<ResponseStatuss> handleNoTransactionFoundException(NoTransactionFoundException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(400);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.NOT_FOUND);
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
	
	@ExceptionHandler(FromAndToPhoneCanNotBeSameException.class)
	public ResponseEntity<ResponseStatuss> handleFromAndToPhoneCanNotBeSameException(FromAndToPhoneCanNotBeSameException details){
		ResponseStatuss response=new ResponseStatuss();
		response.setMessage(details.getLocalizedMessage());
		response.setStatusCode(402);
		return new ResponseEntity<ResponseStatuss>(response, HttpStatus.BAD_REQUEST);
	}

}
