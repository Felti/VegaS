package com.vega.app.dtos;


import com.vega.app.dtos.simple.SimpleStockDTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class SizeDTO extends BaseDTO {

	private String name;

	private String displayName;
	
	private Integer nbrAvailable;

	private SimpleStockDTO stock;

}
