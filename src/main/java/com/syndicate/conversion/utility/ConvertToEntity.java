package com.syndicate.conversion.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertToEntity {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public <T, S> T map(S source, Class<T> targetClass) {
	    return modelMapper.map(source, targetClass);
	}

}
