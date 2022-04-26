package com.vega.app.dtos.simple;

import com.vega.app.dtos.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimpleUserDTO extends BaseDTO {
	
	private String firstName;

	private String lastName;
	
	private String pictureUrl;

	private String email;

	private String phoneNumber;

	private String login;

	private String password;

	private Boolean isEnabled;
	
	public SimpleUserDTO(Long id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public SimpleUserDTO(Long id, String firstName, String lastName, String pictureUrl) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.pictureUrl = pictureUrl;
	}

	public SimpleUserDTO(Long id, String firstName, String lastName, String email, String phoneNumber, String login,
			String password, Boolean isEnabled) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.login = login;
		this.password = password;
		this.isEnabled = isEnabled;
	}

	public SimpleUserDTO(Long id, String firstName, String lastName, String login, String password, Boolean isEnabled) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.isEnabled = isEnabled;
	}

}
