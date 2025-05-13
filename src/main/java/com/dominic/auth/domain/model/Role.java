package com.dominic.auth.domain.model;

import lombok.Getter;

@Getter
public enum Role {
	USER("일반 유저"),
	ADMIN("관리자");

	private final String description;

	Role(String description) {
		this.description = description;
	}
}
