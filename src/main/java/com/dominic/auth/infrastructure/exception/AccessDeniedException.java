package com.dominic.auth.infrastructure.exception;

public class AccessDeniedException extends BaseException {

	public AccessDeniedException() {
		super("ACCESS_DENIED", "접근 권한이 없습니다.");
	}
}
