package com.vega.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageableDTO {

	private int page;

	private int size;

	public PageableDTO(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}

}
