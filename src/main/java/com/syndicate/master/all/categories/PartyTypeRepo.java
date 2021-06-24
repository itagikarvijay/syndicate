package com.syndicate.master.all.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyTypeRepo extends  JpaRepository<PartyType, Long> {

}
