package com.syndicate.master.product;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductRatesDTO {

	private Long id;
	private Long productId;
	private float rate;
	private LocalDate wef;
}
