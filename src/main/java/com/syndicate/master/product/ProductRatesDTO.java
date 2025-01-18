package com.syndicate.master.product;

import java.time.LocalDate;

import lombok.Data;

public class ProductRatesDTO {

	private Long id;
	private Long productId;
	private Long storeId;	
	
	private float rate;
	private float gst;
	private float cgst;
	private float sgst;	
	
	private LocalDate wef;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getGst() {
		return gst;
	}

	public void setGst(float gst) {
		this.gst = gst;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public LocalDate getWef() {
		return wef;
	}

	public void setWef(LocalDate wef) {
		this.wef = wef;
	}
}
