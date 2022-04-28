package com.vega.app.dtos;

import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.dtos.simple.SimpleFeatureDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ColorDTO extends SimpleColorDTO {

	private SimpleFeatureDTO feature;

}
