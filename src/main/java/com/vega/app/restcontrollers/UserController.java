package com.vega.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.entities.CustomResponse;
import com.vega.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PutMapping("add-friend/{currentuser}/{friendId}")
	public ResponseEntity<CustomResponse<Void>> create(@PathVariable(value = "currentuser") Long currentuser,
			@PathVariable(value ="friendId") Long friendId) {
		userService.addFriend(currentuser, friendId);
		return new ResponseEntity<>(new CustomResponse<>(null, "Friend successfully added"),
				HttpStatus.OK);
	}

}
