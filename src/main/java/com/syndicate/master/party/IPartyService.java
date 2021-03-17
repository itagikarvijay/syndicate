package com.syndicate.master.party;

import java.util.List;

public interface IPartyService {
	PartyDTO findByGstNo(String gstNo);

	List<PartyDTO> findAll();
	
	PartyDTO update(PartyDTO partyDTO);
}
