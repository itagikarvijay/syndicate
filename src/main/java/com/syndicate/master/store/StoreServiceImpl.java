package com.syndicate.master.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;

@Service
public class StoreServiceImpl implements IStoreService {

	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	ConvertToDto convertToDto;
	
	@Override
	public List<StoreDTO> getAllStore() {
		return convertToDto.mapList(storeRepo.findAll(), StoreDTO.class);
	}

}
