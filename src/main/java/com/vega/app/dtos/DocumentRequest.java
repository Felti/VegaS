package com.vega.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DocumentRequest {
	
	private PageableDTO pageable;
	
	private Long idOwner;
	
	private Boolean isOwner;
	
	private Boolean deleted;

}
