package com.vega.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public final class SecurityUtils {

	public static String encodePassword(String password) {
		Assert.notNull(password, "Cannot incode password, password is null");
		return new BCryptPasswordEncoder().encode(password);
	}

}
