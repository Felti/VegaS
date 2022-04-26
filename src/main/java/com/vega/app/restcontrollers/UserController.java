package com.vega.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.UserDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login/{login}")
	public ResponseEntity<CustomResponse<UserDTO>> getByLogin(@PathVariable(name = "login") String login) {
		return new ResponseEntity<>(new CustomResponse<>(userService.getByLogin(login)), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CustomResponse<UserDTO>> getByid(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(new CustomResponse<>(userService.getDTOById(id)), HttpStatus.OK);
	}

}
