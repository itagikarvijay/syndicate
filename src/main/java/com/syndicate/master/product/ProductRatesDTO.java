package com.syndicate.master.product;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductRatesDTO {

	private Long id;
	private Long productId;
	private Long storeId;	
	
	private float rate;
	private float gst;
	private float cgst;
	private float sgst;	
	
	private LocalDate wef;
}
