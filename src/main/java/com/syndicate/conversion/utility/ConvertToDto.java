package com.syndicate.conversion.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertToDto {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
	
	public <T, S> T mapList(S source, Class<T> targetClass) {
	    return modelMapper.map(source, targetClass);
	}


}
