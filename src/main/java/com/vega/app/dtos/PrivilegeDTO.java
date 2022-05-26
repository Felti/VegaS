package com.vega.app.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrivilegeDTO extends BaseDTO {

	private String name;

	public PrivilegeDTO(Long id, String name) {
		super(id);
		this.name = name;
	}

	public PrivilegeDTO(Long id, String name, Date createdAt, Date modifiedAt, Boolean deleted) {
		super(id, deleted, createdAt, modifiedAt);
		this.name = name;
	}

}
