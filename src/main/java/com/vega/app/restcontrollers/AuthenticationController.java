package com.vega.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.JwtRequest;
import com.vega.app.dtos.JwtResponse;
import com.vega.app.dtos.SimpleUserDTO;
import com.vega.app.dtos.UserDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.UserService;
import com.vega.app.services.impl.CustomUserDetails;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;


	@PostMapping("/sign-in")
	public ResponseEntity<CustomResponse<JwtResponse>> createAuthenticationToken(@RequestBody JwtRequest credentials)
			throws DisabledException, BadCredentialsException {
		return new ResponseEntity<>(new CustomResponse<>(userService.signIn(credentials), "Authentication successful"),
				HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<CustomResponse<UserDTO>> signUp(@RequestBody SimpleUserDTO newUser) {
		return new ResponseEntity<>(new CustomResponse<>(userService.signUp(newUser), "Successfully signed up !"),
				HttpStatus.OK);
	}

	@GetMapping("/currentUser")
	public ResponseEntity<CustomResponse<CustomUserDetails>> getCurrentUser() {
		return new ResponseEntity<>(new CustomResponse<>(userService.getCurrentUser(), "Fetched current user"),
				HttpStatus.OK);
	}

}
