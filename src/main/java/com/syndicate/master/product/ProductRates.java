package com.syndicate.master.product;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "product_rates_taxes")
public class ProductRates implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
//	@Column(name="product_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	@Column(name = "rate")
	private float rate;
	@Column(name = "gst_percent", nullable = false)
	private float gst;
	@Column(name = "cgst_percent", nullable = false)
	private float cgst;
	@Column(name = "sgst_percent", nullable = false)
	private float sgst;
	@Column(name = "wef_date")
	private LocalDate wef;
	@Column(name = "store_id")
	private Long storeId;
}
