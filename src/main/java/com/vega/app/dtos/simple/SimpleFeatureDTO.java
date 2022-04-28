package com.vega.app.dtos.simple;

import java.util.Set;

import com.vega.app.dtos.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimpleFeatureDTO extends BaseDTO {

	private String name;

	private Integer nbrAvailable;

	private Set<SimpleColorDTO> colors;

	public SimpleFeatureDTO(Long id, String name, Integer nbrAvailable) {
		super(id);
		this.name = name;
		this.nbrAvailable = nbrAvailable;
	}

}
