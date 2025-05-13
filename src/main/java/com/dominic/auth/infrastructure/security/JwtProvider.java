package com.dominic.auth.infrastructure.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dominic.auth.domain.model.User;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secretKeyString;
	private SecretKey secretKey;

	@Getter
	@Value("${jwt.access-expiration}")
	private Long accessExpiration;

	@Getter
	@Value("${jwt.refresh-expiration}")
	private Long refreshExpiration;

	// 1. SecretKey 초기화
	@PostConstruct
	public void init() {
		this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
	}

	// 2. Access Token 생성
	public String createAccessToken(User user) {
		return Jwts.builder()
			.setSubject(user.getUsername())
			.claim("role", user.getRole().name())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
			.signWith(secretKey)
			.compact();
	}

	// 3. Refresh Token 생성
	public String createRefreshToken(User user) {
		return Jwts.builder()
			.setSubject(user.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
			.signWith(secretKey)
			.compact();
	}

	// 4. 토큰 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	// 5. 토큰에서 사용자 이름 추출
	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}
}
