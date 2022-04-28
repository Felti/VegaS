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
public class SimpleColorDTO extends BaseDTO {

	private String name;

	private Integer available;

	public SimpleColorDTO(Long id, String name, Integer available) {
		super(id);
		this.name = name;
		this.available = available;
	}

}
