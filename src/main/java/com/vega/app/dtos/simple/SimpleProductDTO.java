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
public class SimpleProductDTO extends BaseDTO {

	private String name;

	private SimpleColorDTO color;

	private SimpleFeatureDTO size;

	private Double price;

	private Long stockId;

	public SimpleProductDTO(Long id, String name, String color, String size, Double price) {
		super(id);
		this.name = name;
		this.color = new SimpleColorDTO(color);
		this.size = new SimpleFeatureDTO(size);
		this.price = price;

	}

}
