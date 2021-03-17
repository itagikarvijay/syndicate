package com.syndicate.master.party;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;
import com.syndicate.conversion.utility.ConvertToEntity;
import com.syndicate.exception.NotFoundException;

@Service
public class PartyServiceImpl implements IPartyService {

	@Autowired
	IPartyRepo partyRepo;

	@Autowired
	ConvertToDto convertToDto;
	
	@Autowired
	ConvertToEntity convertToEntity;

	@Override
	public PartyDTO findByGstNo(String gstNo) {
		
		Optional<Party> p = partyRepo.findByGstNo(gstNo);
		if(p.isEmpty())
			throw new NotFoundException("Party Not Found.!");
		
		return convertToDto.mapList(p.get(), PartyDTO.class);
	}

	@Override
	public List<PartyDTO> findAll() {
		return convertToDto.mapList(partyRepo.findAll(), PartyDTO.class);
	}

	@Override
	public PartyDTO update(PartyDTO partyDTO) {
		Party p = partyRepo.save(convertToEntity.map(partyDTO, Party.class));
		return convertToDto.mapList(p, PartyDTO.class);
	}

}
