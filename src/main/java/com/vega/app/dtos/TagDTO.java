package com.vega.app.dtos;

import java.util.Set;

import com.vega.app.dtos.simple.SimpleCategoryDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagDTO  extends SimpleTagDTO{
	
	private SimpleCategoryDTO category;
	
	private Set<SimpleStockDTO> stocks;
	
	

}
