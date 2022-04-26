package com.vega.app.entities.request;


import com.vega.app.dtos.simple.SimpleUserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddUserRequest {

	private SimpleUserDTO user;
	
	private String role;
	
	
}
