package com.syndicate.master.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {

}
