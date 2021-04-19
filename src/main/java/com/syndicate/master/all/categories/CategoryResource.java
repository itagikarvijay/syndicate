package com.syndicate.master.all.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categoryType")
public class CategoryResource {

	@Autowired
	ICategoryService<CategoryDTO> categoryService;

	@RequestMapping("/party")
	public List<CategoryDTO> partyType(@RequestParam("category") String category) {
		return categoryService.findAll(category, CategoryDTO.class);
	}

	@RequestMapping("/product")
	public ResponseEntity<List<CategoryDTO>> productCategory(@RequestParam("category") String category) {
		return new ResponseEntity<>(categoryService.findAll(category, CategoryDTO.class), HttpStatus.OK);
	}

}
