package com.syndicate.master.all.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;
import com.syndicate.exception.NotFoundException;

@Service
public class CategoryServiceImpl<T> implements ICategoryService<T> {

	@Autowired
	PartyTypeRepo partyTypeRepo;

	@Autowired
	ProductCategoryRepo productCategoryRepo;

	@Autowired
	ProductCategoryRepo productCategory;
	
	@Autowired
	UomCategoryRepo uomCategoryRepo;

	@Autowired
	DepartmentRepo departmentRepo;

	@Autowired
	CategoryRepo categoryRepo;
	
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
		case "UOM_CATEGORY":
			list = (List<T>) uomCategoryRepo.findAll();
			break;
		case "DEPT_TYPE":
			list = (List<T>) departmentRepo.findAll();
			break;			
		default:
//			System.out.println("All categories");
			list = (List<T>) categoryRepo.findAll();
			break;
		}
		if (list.isEmpty())
			throw new NotFoundException("List is empty.!");
		return (List<T>) convertToDto.mapList(list, clazzDTO);
	}

	@Override
	public Long save(Category c) {
		Category savedCategory = categoryRepo.save(c);
		return savedCategory.getId();
	}



}
