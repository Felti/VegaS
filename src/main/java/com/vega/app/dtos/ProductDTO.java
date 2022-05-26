package com.vega.app.dtos;

import com.vega.app.dtos.simple.SimpleOrderDTO;
import com.vega.app.dtos.simple.SimpleProductDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;
import com.vega.app.dtos.simple.SimpleUserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO extends SimpleProductDTO {

	private SimpleOrderDTO order;

	private SimpleStockDTO stock;

	private SimpleUserDTO customer;

}
