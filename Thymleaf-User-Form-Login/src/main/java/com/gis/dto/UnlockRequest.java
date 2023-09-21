package com.gis.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnlockRequest {

	@NotBlank(message = "This field should not be empty")
	private String tempPassword;

	@NotBlank(message = "This field should not be empty")
	private String newPassword;

	@NotBlank(message = "This field should not be empty")
	private String confirmPassword;

	@NotBlank(message = "This field should not be empty")
	private String email;

}
