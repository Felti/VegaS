package com.vega.app.dtos;

import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FeatureDTO extends SimpleFeatureDTO {

	private SimpleStockDTO stock;

}
