package com.vega.app.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.dtos.RoleDTO;
import com.vega.app.dtos.simple.SimpleUserDTO;
import com.vega.app.services.PrivilegeService;
import com.vega.app.services.RoleService;
import com.vega.app.services.UserService;

@Service
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PrivilegeService privilegeService;

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleDTO> roles, Long userId) {
		return getGrantedAuthorities(getUserAuthorities(roles, userId));
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (String authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority));
		}

		return grantedAuthorities;
	}

	private List<String> getUserAuthorities(Collection<RoleDTO> roles, Long userId) {
		List<String> authorities = new ArrayList<>();
		Set<PrivilegeDTO> temporaryList = new HashSet<>();

		// Extract role names and privileges names linked to role
		for (RoleDTO role : roles) {
			authorities.add(role.getName());
		}

		Set<PrivilegeDTO> userPrivileges = privilegeService.getByUserId(userId);
		temporaryList.addAll(userPrivileges);

		for (PrivilegeDTO privilege : temporaryList) {
			authorities.add(privilege.getName());
		}

		return authorities;
	}

	// Checks if the user exists and creates a new instance if CustomUserDetails to
	// be used by the system
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		SimpleUserDTO user = userService.getUserByLogin(login);
		
		if (Boolean.FALSE.equals(ObjectUtils.isEmpty(user))) {

			Collection<RoleDTO> roles = roleService.getByUserId(user.getId());

			return new CustomUserDetails(user, getAuthorities(roles, user.getId()));

		} else {
			throw new UsernameNotFoundException("Aucun utilisateur trouvé avec le login : " + login);
		}

	}

}
