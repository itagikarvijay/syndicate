package com.syndicate.master.role;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/role")
@RestController
public class RoleResource {

	private static final Logger logger = LoggerFactory.getLogger(RoleResource.class);

	@Autowired
	IRoleService roleService;

	@GetMapping("/fetchAllRole")
	public ResponseEntity<List<RoleDTO>> fetchAllRoles() {
		logger.info("fetching all roles");
		return new ResponseEntity<List<RoleDTO>>(roleService.fetchAllRole(), HttpStatus.OK);
	}
}
