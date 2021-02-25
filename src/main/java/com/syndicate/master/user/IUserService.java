/**
 * 
 */
package com.syndicate.master.user;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author User
 *
 */
public interface IUserService extends UserDetailsService {

	String saveOne(UserDTO user) throws Exception;
	UserDTO findOne(String name);

}
