package com.dominic.auth.infrastructure.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private String code;

	public BaseException(String code, String message) {
		super(message);
		this.code = code;
	}
}
