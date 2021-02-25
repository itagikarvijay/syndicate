/**
 * 
 */
package com.syndicate.master.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author User
 *
 */
@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {

	Optional<User> findByName(String name);
}
