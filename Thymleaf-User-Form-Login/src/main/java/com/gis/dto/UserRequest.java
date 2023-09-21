package com.gis.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

	@NotEmpty(message = "Name is required.")
	private String name;

	@NotEmpty(message = "Email is required.")
	@Email(message = "Enter Valid Email")
	private String email;


	@NotBlank(message = "Gender is required.")
	private String gender;

}
