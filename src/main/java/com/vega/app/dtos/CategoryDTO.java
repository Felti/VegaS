package com.vega.app.dtos;

import java.util.Set;

import com.vega.app.dtos.simple.SimpleCategoryDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO extends SimpleCategoryDTO {

	private Set<SimpleTagDTO> tags;

	public CategoryDTO(Long id, String name) {
		super(id, name);
	}

}
