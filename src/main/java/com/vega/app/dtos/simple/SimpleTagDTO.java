package com.vega.app.dtos.simple;

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
	

}
