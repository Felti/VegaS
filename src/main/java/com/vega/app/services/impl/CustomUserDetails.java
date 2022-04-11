package com.vega.app.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vega.app.dtos.SimpleUserDTO;
import com.vega.app.dtos.UserDTO;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 3866274223316939121L;
	
	private final transient SimpleUserDTO user;
	private final Collection<? extends GrantedAuthority> grantedAuthorities;

	public CustomUserDetails(UserDTO user) {
		this(user, new ArrayList<>());
	}

	public CustomUserDetails(SimpleUserDTO user, Collection<? extends GrantedAuthority> grantedAuthorities) {
		this.user = user;
		this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getIsEnabled();

	}

}
