package com.syndicate.master.party;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPartyRepo extends JpaRepository<Party, Long> {

	@Query("SELECT p FROM com.syndicate.master.party.Party as p "
			+ "WHERE p.gstin =:gstNo")
	Optional<Party> findByGstNo(String gstNo);
}
