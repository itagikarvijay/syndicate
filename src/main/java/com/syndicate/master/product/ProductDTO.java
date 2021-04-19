package com.syndicate.master.product;

import com.syndicate.master.all.categories.CategoryDTO;

import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String name;
	private Long prodCategryId;
	private String hsnCode;
	private Boolean service;
	private float gstPercentage;
	private float cgstPercentage;
	private float sgstPercentage;
	private Boolean inactive;
	private Boolean isDeleted;
	private CategoryDTO productCategory;
	private ProductRatesDTO productRates;
}
