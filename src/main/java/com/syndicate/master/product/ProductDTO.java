package com.syndicate.master.product;

import java.util.List;

import com.syndicate.BaseDTO;
import com.syndicate.master.all.categories.CategoryDTO;
import com.syndicate.tran.voucher.ProductStock;

import lombok.Data;

@Data
public class ProductDTO extends BaseDTO {

	private Long id;
	private String name;
	private Long prodCategryId;
	private String hsnCode;
	private Boolean service;
	private float stock;
	private float gstPercentage;
	private float cgstPercentage;
	private float sgstPercentage;
	private float rate;
	private Boolean inactive;
	private Boolean isDeleted;
	private Long uom;
	private String image;
	private CategoryDTO productCategory;
	private CategoryDTO uomCategory;
	private List<ProductRatesDTO> productRates;
	private List<ProductStock> productStock;
//	private String strUom;
//	private String category;

//	public ProductDTO(String name, String strUom, String category, String hsnCode, float stock, float rate,
//			float gstPercentage, float cgstPercentage, float sgstPercentage) {
//		this.name = name;
//		this.strUom = strUom;
//		this.category = category;
//		this.hsnCode = hsnCode;
//		this.stock = stock;
//		this.rate = rate;
//		this.gstPercentage = gstPercentage;
//		this.cgstPercentage = cgstPercentage;
//		this.sgstPercentage = sgstPercentage;
//	}
//
//	public ProductDTO(String name, String strUom, String category, String hsnCode, String strStock, String strRate,
//			String strGstPercentage, String cgstPercentage, String sgstPercentage) {
//
//	}

}
