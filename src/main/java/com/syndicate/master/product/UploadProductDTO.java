package com.syndicate.master.product;

import com.syndicate.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UploadProductDTO extends BaseDTO {

	private String product;
	private String uom;
	private String category;
	private String hsncode;
	private String qty;
	private String rate;
	private String gst;
	private String cgst;
	private String sgst;

}
