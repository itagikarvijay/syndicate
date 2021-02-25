/**
 * 
 */
package com.syndicate.master.user;

import java.util.Set;

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
	
	private Set<Role> roles;
}
