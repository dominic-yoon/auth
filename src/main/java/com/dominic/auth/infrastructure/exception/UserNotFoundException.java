package com.dominic.auth.infrastructure.exception;

public class UserNotFoundException extends BaseException {

	public UserNotFoundException() {
		super("USER_NOT_FOUND", "유저 정보가 존재하지 않습니다.");
	}
}
