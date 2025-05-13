package com.dominic.auth.domain.model;

import com.dominic.auth.presentation.request.SignupRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

	@Builder
	public User(Long id, String username, String password, String nickname, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String nickname;

	@Enumerated(EnumType.STRING)
	private Role role;

	public static User signup(SignupRequestDto request) {
		return User.builder()
			.username(request.getUsername())
			.password(request.getPassword())
			.nickname(request.getNickname())
			.role(Role.USER)
			.build();
	}

	public void updateRole() {
		this.role = Role.ADMIN;
	}
}
