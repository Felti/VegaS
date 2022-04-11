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
public class SimpleRoleDTO extends BaseDTO{

	private String name;

	private Set<PrivilegeDTO> privileges;

	public SimpleRoleDTO(Long id, String name) {
		super(id);
		this.name = name;
	}
}
