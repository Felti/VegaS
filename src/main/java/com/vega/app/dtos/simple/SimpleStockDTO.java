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
public class SimpleStockDTO extends BaseDTO {

	private String name;

	private String description;

	private Integer quantity;

	private Double unitPrice;

	private Double sellingPrice;

	private SimpleUserDTO provider;

	public SimpleStockDTO(Long id) {
		super(id);
	}

}
