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
public class PageRequest {
	
	private PageableDTO pageRequest;
	
	private Boolean deleted;

}
