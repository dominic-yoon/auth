package com.dominic.auth.presentation.request;

import com.dominic.auth.domain.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRoleRequestDto {

	private Role role;
}
