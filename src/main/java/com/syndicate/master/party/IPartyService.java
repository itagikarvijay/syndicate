package com.syndicate.master.party;

import java.util.List;

public interface IPartyService {
	
	PartyDTO findByGstNo(String gstNo);

	List<PartyDTO> findAll();
	List<PartyDTO> findAll(Integer partyTypeId);
	
	PartyDTO update(PartyDTO partyDTO);
}
