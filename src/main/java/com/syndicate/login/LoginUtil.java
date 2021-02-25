package com.syndicate.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public String hashPassword(String pwd) {
		String encodedPassword = bCryptPasswordEncoder.encode(pwd);
		return encodedPassword;
	}

	public boolean comparePassword(String passwordFromUI, String passwordFromDB) {
		if (bCryptPasswordEncoder.matches(passwordFromUI, passwordFromDB)) {
			return true;
		} else {
			return false;
		}
	}
}
