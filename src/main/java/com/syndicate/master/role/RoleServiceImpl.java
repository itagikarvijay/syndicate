package com.syndicate.master.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IRoleRepo roleRepo;

	@Autowired
	private ConvertToDto convertToDto;

	@Override
	public List<RoleDTO> fetchAllRole() {
		return convertToDto.mapList(roleRepo.findAll(), RoleDTO.class);
	}

}
