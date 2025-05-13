package com.dominic.auth.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<Map<String, Object>> handleBaseException(BaseException e) {
		Map<String, Object> error = Map.of(
			"error", Map.of(
				"code", e.getCode(),
				"message", e.getMessage()
			)
		);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
