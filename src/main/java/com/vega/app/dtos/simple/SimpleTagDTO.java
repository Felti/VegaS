package com.vega.app.dtos.simple;

import java.util.Date;

import com.vega.app.dtos.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleTagDTO extends BaseDTO {

	private String name;

	public SimpleTagDTO(Long id, String name) {
		super(id);
		this.name = name;
	}

	public SimpleTagDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt, String name) {
		super(id, deleted, createdAt, modifiedAt);
		this.name = name;
	}

}
