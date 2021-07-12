package com.syndicate.conversion.utility;

import java.util.List;

import com.syndicate.master.product.UploadProductDTO;

import lombok.Data;

@Data
public class WrapperClz {
	private Long storeId;
	private List<UploadProductDTO> successList;
}
