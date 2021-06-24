package com.syndicate.tran.voucher;

import lombok.Data;

@Data
public class VoucherDetailsDTO {
	
	private Long id;
	private Long voucherId;
	private Long storeId;
	private Long productId;
	private String hsnCode;
	private Float gstPercent;
	private Float rate;
	private Float inQty;
	private Float outQty;
	private Float netAmount;
	private Float taxableAmount;
	private Float cgstPercent;
	private Float cgstAmount;
	private Float sgstPercent;
	private Float sgstAmount;
	private Float igstPercent;
	private Float igstAmount;
	private Float cbQty;
	private Voucher voucher;
}
