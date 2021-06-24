package com.syndicate.tran.voucher;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class VoucherDTO {
	private Long id;
	private LocalDate voucherDate;
	private Long voucherTypeId;
	private Long storeId;
	private Long userId;
	private String party;
	private Long partyId;
	private Float voucherAmountCredit;
	private Float voucherAmountDebit;
	private Long shipTo;
	private String gstin;
	private Long shipperId;
	private String vehicleNo;
	private String placeOfSupply;
	private Float itds;
	private Float itcs;
	private Integer crDr;
	private String type;
	private String typeNumber;
	private List<VoucherDetailsDTO> voucherDetails;
}
