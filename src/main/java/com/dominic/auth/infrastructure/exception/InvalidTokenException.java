package com.dominic.auth.infrastructure.exception;

public class InvalidTokenException extends BaseException {

	public InvalidTokenException() {
		super("INVALID_TOKEN", "유효하지 않은 인증 토큰입니다.");
	}
}
