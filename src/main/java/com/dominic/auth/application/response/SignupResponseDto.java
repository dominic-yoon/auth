package com.dominic.auth.application.response;

import java.util.List;

import com.dominic.auth.domain.model.Role;
import com.dominic.auth.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupResponseDto {

	private String username;
	private String nickname;
	private List<RoleDto> roles;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	private static class RoleDto {
		private Role role;
	}

	public static SignupResponseDto from(User user) {
		return SignupResponseDto.builder()
			.username(user.getUsername())
			.nickname(user.getNickname())
			.roles(List.of(new RoleDto(user.getRole())))
			.build();
	}
}
