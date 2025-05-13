package com.dominic.auth.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominic.auth.application.response.LoginResponseDto;
import com.dominic.auth.application.response.SignupResponseDto;
import com.dominic.auth.application.service.UserService;
import com.dominic.auth.presentation.request.LoginRequestDto;
import com.dominic.auth.presentation.request.SignupRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final UserService userService;

	@Operation(summary = "회원가입", description = "회원 정보를 받아 회원가입 처리합니다.")
	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto req) {
		SignupResponseDto res = userService.signup(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@Operation(summary = "로그인", description = "회원 정보로 로그인을 합니다.")
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto req) {
		LoginResponseDto res = userService.login(req);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
