package com.dominic.auth.application.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominic.auth.application.response.UpdateUserRoleResponseDto;
import com.dominic.auth.application.response.LoginResponseDto;
import com.dominic.auth.application.response.SignupResponseDto;
import com.dominic.auth.domain.model.Role;
import com.dominic.auth.domain.model.User;
import com.dominic.auth.domain.repository.UserRepository;
import com.dominic.auth.infrastructure.security.JwtProvider;
import com.dominic.auth.presentation.request.LoginRequestDto;
import com.dominic.auth.presentation.request.SignupRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public SignupResponseDto signup(SignupRequestDto req) {
		// 1. 사용자 중복 확인
		if (userRepository.findByUsername(req.getUsername()).isPresent()) {
			throw new IllegalArgumentException("이미 가입된 사용자입니다.");
		}

		// 2. 사용자 생성
		User user = User.builder()
			.username(req.getUsername())
			.password(passwordEncoder.encode(req.getPassword()))
			.nickname(req.getNickname())
			.role(req.getRole())
			.build();

		// 3. DB 저장
		User newUser = userRepository.save(user);

		// 4. 응답 DTO 구성
		return SignupResponseDto.from(newUser);
	}

	@Transactional
	public LoginResponseDto login(LoginRequestDto req) {
		User user = userRepository.findByUsername(req.getUsername())
			.orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));

		if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
		}

		String token = jwtProvider.createAccessToken(user);

		return LoginResponseDto.from(token);
	}

	@Transactional
	public UpdateUserRoleResponseDto updateUserRole(Long userId) {

		// 1. 현재 로그인한 사용자 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();

		log.info("현재 사용자 이름: {}", currentUserName);

		User currentUser = userRepository.findByUsername(currentUserName)
			.orElseThrow(() -> new IllegalArgumentException("인증된 사용자 정보를 찾을 수 없습니다."));

		// 2. 사용자 권한 확인
		if (!currentUser.getRole().equals(Role.ADMIN)) {
			throw new IllegalArgumentException("관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다.");
		}

		// 3. 권한 부여할 사용자 확인
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));

		// 4. 권한 업데이트
		user.updateRole();

		return UpdateUserRoleResponseDto.from(user);
	}
}
