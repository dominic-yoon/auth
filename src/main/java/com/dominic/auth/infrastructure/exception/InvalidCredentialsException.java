package com.dominic.auth.infrastructure.exception;

public class InvalidCredentialsException extends BaseException {

	public InvalidCredentialsException() {
		super("INVALID_CREDENTIALS", "아이디 또는 비밀번호가 올바르지 않습니다.");
	}
}
