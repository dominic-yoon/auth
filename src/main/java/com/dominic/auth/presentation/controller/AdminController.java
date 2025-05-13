package com.dominic.auth.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominic.auth.application.response.UpdateUserRoleResponseDto;
import com.dominic.auth.application.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "권한 부여", description = "회원 ID로 ADMIN 권한을 부여합니다.")
	@PatchMapping("/users/{userId}/roles")
	public ResponseEntity<UpdateUserRoleResponseDto> updateUserRole(@PathVariable Long userId) {
		UpdateUserRoleResponseDto res = userService.updateUserRole(userId);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
