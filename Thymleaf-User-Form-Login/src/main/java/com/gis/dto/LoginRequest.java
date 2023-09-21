package com.gis.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotEmpty(message = "Email is required.")
	private String email;

	@NotEmpty(message = "Password is required.")	
	private String password;

}
