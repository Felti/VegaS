package com.vega.app.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthRequestDTO {

	private Long externalId;
	
	private String firstName;

	private String lastName;

	private String email;

	private String login;

	private String password;
}
