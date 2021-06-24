package com.syndicate.tran.voucher;

public interface IVoucherService {

	Long sale(VoucherDTO voucherDTO);

	Long purchase(VoucherDTO voucherDTO);

	Long transfer(VoucherDTO voucherDTO);

	Long salesReturn(VoucherDTO voucherDTO);

	Long purchaseReturn(VoucherDTO voucherDTO);

	Long issues(VoucherDTO voucherDTO);

	Long scrap(VoucherDTO voucherDTO);
}
