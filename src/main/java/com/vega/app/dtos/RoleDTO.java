package com.vega.app.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleDTO extends BaseDTO {

	private String name;

	private Set<UserDTO> users;

	private Set<PrivilegeDTO> privileges;

	public RoleDTO(Long id, String name) {
		super(id);
		this.name = name;
	}

	public RoleDTO(Long id, String name, Date createdAt, Date modifiedAt, Boolean deleted) {
		super(id, deleted, createdAt, modifiedAt);
		this.name = name;
		this.privileges = new HashSet<>();
	}

}
