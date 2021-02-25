/**
 * 
 */
package com.syndicate.master.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author User
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	IUserService userService;

	@Autowired
	IUserRepo userRepo;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ROOT')")
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
		try {
			userService.saveOne(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("User Created", HttpStatus.CREATED);
	}
}
