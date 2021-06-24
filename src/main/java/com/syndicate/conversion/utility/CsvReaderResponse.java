package com.syndicate.conversion.utility;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.syndicate.master.product.UploadProductDTO;

public class CsvReaderResponse {

	List<UploadProductDTO> successList = new ArrayList<UploadProductDTO>();
	List<UploadProductDTO> failuerList = new ArrayList<UploadProductDTO>();

	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String errorMessage;

	public List<UploadProductDTO> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<UploadProductDTO> successList) {
		this.successList = successList;
	}

	public List<UploadProductDTO> getFailuerList() {
		return failuerList;
	}

	public void setFailuerList(List<UploadProductDTO> failuerList) {
		this.failuerList = failuerList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
