package com.gis.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> response = new HashMap<>();

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		for (FieldError error : fieldErrors) {
			response.put(error.getField(), error.getDefaultMessage());
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
