/**
 * 
 */
package com.syndicate.master.user;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.syndicate.master.role.Role;

import lombok.Data;

/**
 * @author User
 *
 */
@Data
public class UserDTO {


	private String name;
	
	private String password;
	private String token;
	private String user;
	
	private Set<String> roles;
	
	private boolean found = false;
	
	private String login_failuer_message;
	
	
}
