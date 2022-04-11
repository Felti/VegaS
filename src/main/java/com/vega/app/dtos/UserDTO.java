package com.vega.app.dtos;

import java.util.Set;

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

	private Set<UserDTO> friends;

}
