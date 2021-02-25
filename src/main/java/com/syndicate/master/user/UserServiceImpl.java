/**
 * 
 */
package com.syndicate.master.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syndicate.login.LoginUtil;

/**
 * @author User
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	IUserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	LoginUtil loginUtil;
	
	@Autowired
	HttpServletRequest req;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("User name " + username);

		Optional<User> user = userRepo.findByName(username);
		
		if (user.get() == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(user.get().getName(), user.get().getPassword(),
				addRole(user));
	}

	private Collection<? extends GrantedAuthority> addRole(Optional<User> user) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		user.get().getRoles().forEach(r -> {
			SimpleGrantedAuthority s = new SimpleGrantedAuthority("ROLE_"+r.getRole());
			list.add(s);
		});
		req.getSession().setAttribute("roles", list);
		return list;
	}

	@Override
	public UserDTO findOne(String name) {
		Optional<User> ud = userRepo.findByName(name);
		if (ud.isPresent())
			return modelMapper.map(ud.get(), UserDTO.class);
		else
			return null;
	}

	@Override
	public String saveOne(UserDTO userDTO) throws Exception {
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(loginUtil.hashPassword(userDTO.getPassword()));
		userRepo.save(user);
		return "User created.!";
	}

}
