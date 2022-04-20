package com.vega.app.services;

import java.util.Set;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import com.vega.app.dtos.JwtRequest;
import com.vega.app.dtos.JwtResponse;
import com.vega.app.dtos.SimpleUserDTO;
import com.vega.app.dtos.UserDTO;
import com.vega.app.entities.User;
import com.vega.app.services.impl.CustomUserDetails;

public interface UserService {

	SimpleUserDTO getUserByLogin(String login);

	JwtResponse signIn(JwtRequest jwtRequest) throws BadCredentialsException, DisabledException;

	UserDTO signUp(SimpleUserDTO simpleUserDTO);

	CustomUserDetails getCurrentUser();

	Set<UserDTO> getAll();

	User getById(Long id);
	
	SimpleUserDTO getDTOBasicById(Long id);
	
	void addFriend(Long currentUser,Long friendId);

	void removeFriend(Long currentUserId, Long friendId);

	UserDTO create(UserDTO userDTO);

	UserDTO edit(Long id);

	void delete(Long id);

	SimpleUserDTO mapEntityToSimpleDTO(User user);

	User mapSimpleDTOToEntity(SimpleUserDTO simpleUser);

	UserDTO mapEntityToDTO(User user);

	User mapDTOToEntity(UserDTO user);


}
