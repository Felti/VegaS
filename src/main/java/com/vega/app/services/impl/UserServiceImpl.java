package com.vega.app.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.JwtRequest;
import com.vega.app.dtos.JwtResponse;
import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.dtos.RoleDTO;
import com.vega.app.dtos.UserDTO;
import com.vega.app.dtos.simple.SimpleUserDTO;
import com.vega.app.entities.Role;
import com.vega.app.entities.User;
import com.vega.app.enums.RoleEnums;
import com.vega.app.exceptions.ValueIsNotUnique;
import com.vega.app.repositories.UserRepository;
import com.vega.app.services.PrivilegeService;
import com.vega.app.services.RoleService;
import com.vega.app.services.UserService;
import com.vega.app.utils.JwtTokenUtil;
import com.vega.app.utils.SecurityUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrivilegeService privilegeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsImpl userDetailsImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	ModelMapper mapper;

	private void authenticate(String login, String password) throws DisabledException, BadCredentialsException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		}
	}

	@Override
	public JwtResponse signIn(JwtRequest jwtRequest) throws BadCredentialsException, DisabledException {
		authenticate(jwtRequest.getLogin(), jwtRequest.getPassword());
		var userDetails = userDetailsImpl.loadUserByUsername(jwtRequest.getLogin());

		var jwtResponse = new JwtResponse();

		jwtResponse.setToken(jwtTokenUtil.generateToken(userDetails));
		return jwtResponse;
	}

	@Override
	public UserDTO signUp(SimpleUserDTO simpleUserDTO) {
		Assert.notNull(simpleUserDTO.getEmail(), ErrorMessages.MISSING_EMAIL);
		Assert.notNull(simpleUserDTO.getLogin(), ErrorMessages.MISSING_LOGIN);
		Assert.notNull(simpleUserDTO.getPassword(), ErrorMessages.MISSING_PASSWORD);
		Assert.notNull(simpleUserDTO.getFirstName(), ErrorMessages.MISSING_FIRSTNAME);
		Assert.notNull(simpleUserDTO.getLastName(), ErrorMessages.MISSING_LASTNAME);

		var user = new User();

		// set basic user info
		if (Boolean.TRUE.equals(isLoginAlreadyExist(simpleUserDTO.getLogin())))
			throw new ValueIsNotUnique(simpleUserDTO.getLogin());

		if (Boolean.TRUE.equals(isEmailAlreadyExist(simpleUserDTO.getEmail())))
			throw new ValueIsNotUnique(simpleUserDTO.getEmail());

		user.setEmail(simpleUserDTO.getEmail().trim());
		user.setLogin(simpleUserDTO.getLogin().trim());

		user.setPassword(SecurityUtils.encodePassword(simpleUserDTO.getPassword()));
		user.setFirstName(simpleUserDTO.getFirstName().trim());
		user.setLastName(simpleUserDTO.getLastName().trim());
		user.setIsEnabled(true);

		// Set roles and privileges
		Set<Role> roles = new HashSet<>();

		var role = roleService.mapSimpleDTOToEntity(roleService.getByNameAndDeleted(RoleEnums.ROLE_USER.toString(), false));
		roles.add(role);

		Set<PrivilegeDTO> privileges = privilegeService.getByRoleId(role.getId());

		user.setRoles(roles);
		user.setPrivileges(privileges.stream().map(p -> privilegeService.mapDTOToEntity(p)).collect(Collectors.toSet()));

		return mapEntityToDTO(userRepository.save(user));
	}

	@Override
	public SimpleUserDTO getUserByLogin(String login) {
		Assert.notNull(login, "Missing Login");
		return userRepository.findByLogin(login.trim());
	}

	@Override
	public UserDTO getByLogin(String login) {
		var simpleUserDto = userRepository.getByLoginIgnoreCase(login);

		Assert.notNull(simpleUserDto, ErrorMessages.OBJECT_NOT_FOUND);

		return getFullUserDTO(simpleUserDto);
	}

	@Override
	public SimpleUserDTO getDTOBasicById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return userRepository.findDTOBasicById(id);
	}

	@Override
	public UserDTO getDTOById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return userRepository.findDTOById(id);
	}

	@Override
	public CustomUserDetails getCurrentUser() {
		var securityContext = SecurityContextHolder.getContext();
		return (CustomUserDetails) securityContext.getAuthentication().getPrincipal();
	}

	@Override
	public Set<UserDTO> getAll() {

		return null;
	}

	@Override
	public User getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) return user.get();
		return null;
	}

	@Override
	public UserDTO edit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDTO getFullUserDTO(SimpleUserDTO dto) {
		Assert.notNull(dto, ErrorMessages.OBJECT_NOT_FOUND);

		var simpleUserDto = userRepository.findDTOBasicById(dto.getId());

		Assert.notNull(simpleUserDto, ErrorMessages.OBJECT_NOT_FOUND);

		var userDto = mapSimpleDTOToDTO(simpleUserDto);

		return appendRolesAndPrivilegesToUserDTO(userDto);
	}

	private UserDTO appendRolesAndPrivilegesToUserDTO(UserDTO userDto) {
		Set<RoleDTO> roles = roleService.getByUserId(userDto.getId());
		Set<PrivilegeDTO> privileges = privilegeService.getByUserId(userDto.getId());

		userDto.setRoles(roles);
		userDto.setPrivileges(privileges);

		return userDto;
	}

	@Override
	public UserDTO mapSimpleDTOToDTO(SimpleUserDTO dto) {
		return mapper.map(dto, UserDTO.class);
	}

	@Override
	public SimpleUserDTO mapEntityToSimpleDTO(User user) {
		return mapper.map(user, SimpleUserDTO.class);
	}

	@Override
	public User mapSimpleDTOToEntity(SimpleUserDTO simpleUser) {
		return mapper.map(simpleUser, User.class);
	}

	@Override
	public UserDTO mapEntityToDTO(User user) {
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public User mapDTOToEntity(UserDTO user) {
		return mapper.map(user, User.class);
	}

	private Boolean isLoginAlreadyExist(String login) {
		return userRepository.countByLogin(login.trim().toLowerCase()) > 0;
	}

	private Boolean isEmailAlreadyExist(String email) {
		return userRepository.countByEmail(email.trim().toLowerCase()) > 0;
	}
}
