package com.syndicate.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syndicate.login.LoginUtil;
import com.syndicate.master.user.UserDTO;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String SECRET = "SECRET_KEY";
	public static final long EXPIRATION_TIME = 900_000; // 15 mins
//	public static final String TOKEN_PREFIX = "Bearer ";
//	public static final String HEADER_STRING = "Authorization";
//	public static final String SIGN_UP_URL = "/api/services/controller/user";

	private AuthenticationManager authenticationManager;
	
	@Autowired
	LoginUtil loginUtil;
	
//	@Autowired
//	public DaoAuthenticationProvider daoAuthenticationProvide;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/api/login");
	}
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		return super.obtainPassword(request);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserDTO creds = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getName(), creds.getPassword()));

		} catch (IOException e) {
		}
		return null;
	}

//	private Collection<? extends GrantedAuthority> addRole() {
//		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//		SimpleGrantedAuthority s = new SimpleGrantedAuthority("ROLE_ADMIN");
//		list.add(s);
//		return list;
//	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException {
		String token = JWT.create().withSubject((auth.getName()))
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));

		String body = (token);

		res.getWriter().write(body);
		res.getWriter().flush();
	}
}
