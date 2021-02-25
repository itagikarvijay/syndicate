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
@RequestMapping("/api")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	IUserService userService;

	@Autowired
	LoginUtil loginUtil;

	@PostMapping("/user/find")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO user) {		
		logger.debug("UserController - User name " + user.getName());
		UserDTO ud = null;
		ud = userService.findOne(user.getName().trim());
		if (ud != null) {
			if (loginUtil.comparePassword(user.getPassword(), ud.getPassword())) {
				return ResponseEntity.ok(Boolean.TRUE);
			} else {
				return ResponseEntity.ok("User name or password is incorrect.!");
			}
		} else {
			return ResponseEntity.ok("User not found.!");
		}
	}
}
