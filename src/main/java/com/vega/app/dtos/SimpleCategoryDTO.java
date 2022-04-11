package com.vega.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimpleCategoryDTO extends BaseDTO{
	
		private String name;

		public SimpleCategoryDTO(Long id, String name) {
			super(id);
			this.name = name;
		}
		
		

}
