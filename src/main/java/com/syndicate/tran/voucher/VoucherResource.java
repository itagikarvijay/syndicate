package com.syndicate.tran.voucher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherResource {

	@Autowired
	IVoucherService iVoucherService;

	@PutMapping("/save")
	public ResponseEntity<Long> save(@RequestBody VoucherDTO voucherDTO) {
		Long invoiceNumber = 0l;
		Long voucherTypeID = voucherDTO.getVoucherTypeId();
		switch (voucherTypeID.intValue()) {
		case 1:
			invoiceNumber = iVoucherService.sale(voucherDTO);
			break;
		case 2:
			invoiceNumber = iVoucherService.purchase(voucherDTO);
			break;
		case 3:
			invoiceNumber = iVoucherService.transfer(voucherDTO);
			break;
		case 4:
			invoiceNumber = iVoucherService.issues(voucherDTO);
			break;
		case 5:
			invoiceNumber = iVoucherService.scrap(voucherDTO);
			break;
		case 6:
			invoiceNumber = iVoucherService.salesReturn(voucherDTO);
			break;
		case 7:
			invoiceNumber = iVoucherService.purchaseReturn(voucherDTO);
			break;
		default:
			break;
		}
		return new ResponseEntity<Long>(invoiceNumber, HttpStatus.OK);
	}

}
