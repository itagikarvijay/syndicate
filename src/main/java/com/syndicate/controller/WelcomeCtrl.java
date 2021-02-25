package com.syndicate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.syndicate.master.user.IUserRepo;
import com.syndicate.master.user.User;
import com.syndicate.master.user.UserDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WelcomeCtrl {
	
	

	@GetMapping("/welcome")
	public String welcomeMessgae() {
		return "Welcome to the syndicate app";
	}
	

	
	
}
