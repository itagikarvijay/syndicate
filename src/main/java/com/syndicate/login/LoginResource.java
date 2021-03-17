package com.syndicate.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syndicate.master.user.IUserService;
import com.syndicate.master.user.UserDTO;

@RestController
@RequestMapping("/api/v1/login")
public class LoginResource {

	private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);

	@Autowired
	IUserService userService;

	@Autowired
	LoginUtil loginUtil;

	@PostMapping("/find")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO user) {
		logger.debug("UserController - User name " + user.getName());
		UserDTO ud = userService.findOne(user.getName().trim());
		if (ud == null) {
			ud = new UserDTO();
			ud.setLogin_failuer_message("User not found.!");
			return ResponseEntity.ok(ud);
		}
		boolean val = loginUtil.comparePassword(user.getPassword(), ud.getPassword());
		ud.setPassword("");
		if (val)
			ud.setFound(Boolean.TRUE);
		else
			ud.setLogin_failuer_message("User name or password is incorrect.!");
		return ResponseEntity.ok(ud);
	}
}
