package com.vega.app.services;

import com.vega.app.dtos.AuthRequestDTO;

public interface UserAuthService {
	
 void saveUserInfo(AuthRequestDTO userinfo);

}
