package com.syndicate.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.syndicate.master.user.IUserRepo;
import com.syndicate.master.user.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public static final String SECRET = "SECRET_KEY";
	public static final long EXPIRATION_TIME = 3600_000; // 15 mins
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
//	public static final String SIGN_UP_URL = "/api/services/controller/user";

	IUserRepo userRepo;

	public JWTAuthorizationFilter(AuthenticationManager authManager, IUserRepo userRepo) {
		super(authManager);
		this.userRepo = userRepo;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_STRING);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	// Reads the JWT from the Authorization header, and then uses JWT to validate
	// the token
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		System.out.println(userRepo);
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();
			if (user != null) {
				// new arraylist means authorities
				return new UsernamePasswordAuthenticationToken(user, null, addRole(user));
			}

			return null;
		}

		return null;
	}

	private Collection<? extends GrantedAuthority> addRole(String user) {
		Optional<User> opt = userRepo.findByName(user);
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		opt.get().getRoles().forEach(consume -> {
			SimpleGrantedAuthority g = new SimpleGrantedAuthority("ROLE_"+ consume.getRole());
			list.add(g);
		});
		return list;
	}
}
