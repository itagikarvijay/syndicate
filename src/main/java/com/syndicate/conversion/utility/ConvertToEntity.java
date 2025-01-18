package com.syndicate.conversion.utility;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ConvertToEntity {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostConstruct
	public void init() {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	
	public <T, S> T map(S source, Class<T> targetClass) {
	    return modelMapper.map(source, targetClass);
	}

}
