package com.syndicate.master.all.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;
import com.syndicate.exception.NotFoundException;
import com.syndicate.master.product.IProductCategoryRepo;

@Service
public class CategoryServiceImpl<T> implements ICategoryService<T> {

	@Autowired
	IPartyTypeRepo partyTypeRepo;

	@Autowired
	IProductCategoryRepo productCategoryRepo;

	@Autowired
	IProductCategoryRepo productCategory;

	@Autowired
	ConvertToDto convertToDto;

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String name, Class<?> clazzDTO) {
		List<T> list = null;
		switch (name) {
		case "PARTY_TYPE":
			list = (List<T>) partyTypeRepo.findAll();
			break;
		case "PRODUCT_CATEGORY":
			list = (List<T>) productCategoryRepo.findAll();
			break;			
		default:
			break;
		}
		if (list.isEmpty())
			throw new NotFoundException("List is empty.!");
		return (List<T>) convertToDto.mapList(list, clazzDTO);
	}

}
