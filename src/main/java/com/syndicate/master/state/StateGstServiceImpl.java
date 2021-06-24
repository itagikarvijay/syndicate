package com.syndicate.master.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;

@Service
public class StateGstServiceImpl implements IStateGstService {

	@Autowired
	StateGstRepo stateGstRepo;

	@Autowired
	ConvertToDto convertToDto;

	@Override
	public List<StateGstDTO> findAll() {
		return convertToDto.mapList(stateGstRepo.findAll(), StateGstDTO.class);
	}

}
