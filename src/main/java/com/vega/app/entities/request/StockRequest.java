package com.vega.app.entities.request;

import com.vega.app.dtos.PageableDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StockRequest {
	
	private PageableDTO pageRequest;
	
	private Boolean deleted;

}
