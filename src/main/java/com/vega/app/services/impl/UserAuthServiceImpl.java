package com.vega.app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vega.app.dtos.AuthRequestDTO;
import com.vega.app.entities.AuthUser;
import com.vega.app.repositories.UserAuthRepo;
import com.vega.app.services.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	UserAuthRepo userAuthRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public void saveUserInfo(AuthRequestDTO userinfo) {

		userAuthRepo.save(mapper.map(userinfo, AuthUser.class));

	}

}
