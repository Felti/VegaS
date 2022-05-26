package com.vega.app.dtos;

import java.util.Set;

import com.vega.app.dtos.simple.SimpleUserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends SimpleUserDTO {

	private Set<RoleDTO> roles;

	private Set<PrivilegeDTO> privileges;

	public UserDTO(Long id, String firstName, String lastName, String phoneNumber, String email) {
		super(id, firstName, lastName, phoneNumber, email);
	}
	
	

}
