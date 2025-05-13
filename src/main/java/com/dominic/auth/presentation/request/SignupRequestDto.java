package com.dominic.auth.presentation.request;

import com.dominic.auth.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDto {

	private String username;
	private String password;
	private String nickname;
}
