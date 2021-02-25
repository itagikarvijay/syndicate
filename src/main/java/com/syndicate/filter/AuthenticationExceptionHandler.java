package com.syndicate.filter;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptionHandler extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationExceptionHandler(String msg) {
		super(msg);
	}

}
