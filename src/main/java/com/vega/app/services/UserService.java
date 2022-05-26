package com.vega.app.services;

import java.util.Set;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import com.vega.app.dtos.JwtRequest;
import com.vega.app.dtos.JwtResponse;
import com.vega.app.dtos.UserDTO;
import com.vega.app.dtos.simple.SimpleUserDTO;
import com.vega.app.entities.User;
import com.vega.app.services.impl.CustomUserDetails;

public interface UserService {

	// Gets the raw data types + roles and privileges
	User getById(Long id);

	// Gets the user DTO simple data types + roles and privileges
	UserDTO getByLogin(String login);

	// Gets the user DTO simple data types + roles and privileges
	UserDTO getDTOById(Long id);

	// Gets the user DTO simple data types
	SimpleUserDTO getUserByLogin(String login);

	// Gets the user DTO simple data types
	SimpleUserDTO getDTOBasicById(Long id);

	// gets Principle
	CustomUserDetails getCurrentUser();

	// sign-in // sign-up
	JwtResponse signIn(JwtRequest jwtRequest) throws BadCredentialsException, DisabledException;

	UserDTO signUp(SimpleUserDTO simpleUserDTO);

	// CRUD user
	Set<UserDTO> getAll();
	
//CRUD user
	Set<SimpleUserDTO> getProviders();

	UserDTO create(UserDTO userDTO);

	UserDTO edit(Long id);

	void delete(Long id);

	// mappers
	SimpleUserDTO mapEntityToSimpleDTO(User user);

	User mapSimpleDTOToEntity(SimpleUserDTO simpleUser);

	UserDTO mapEntityToDTO(User user);

	User mapDTOToEntity(UserDTO user);

	UserDTO mapSimpleDTOToDTO(SimpleUserDTO dto);

}
