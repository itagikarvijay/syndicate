package com.syndicate.master.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateGstRepo extends JpaRepository<StateGst, Long> {

}
