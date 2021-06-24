/**
 * 
 */
package com.syndicate.master.user;

import java.util.Set;

import com.syndicate.master.store.Store;

import lombok.Data;

/**
 * @author User
 *
 */
@Data
public class UserDTO {

	private Long id;
	private String name;
	
	private String password;
	private String token;
	private String user;
	private Long storeId;
	
	private Set<String> roles;
	
	private boolean found = false;
	
	private String login_failuer_message;
	private Store store;
	
}
