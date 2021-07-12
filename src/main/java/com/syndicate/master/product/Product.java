package com.syndicate.master.product;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.syndicate.master.all.categories.Category;
import com.syndicate.tran.voucher.ProductStock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	@Column(name = "product_name", nullable = false, unique = true)
	private String name;
	@Column(name = "prod_category_id")
	private Long prodCategryId;
	@Column(name = "HSNCode")
	private String hsnCode;
	@Column(name = "service", nullable = false)
	private Boolean service;
//	@Column(name = "gst_percent", nullable = false)
//	private float gstPercentage;
//	@Column(name = "cgst_percent", nullable = false)
//	private float cgstPercentage;
//	@Column(name = "sgst_percent", nullable = false)
//	private float sgstPercentage;
	@Column(name = "inactive", nullable = false)
	private Boolean inactive;
	@Column(name = "isDeleted")
	private Boolean isDeleted;
	@Column(name = "uom")
	private Long uom;
	@Lob
	@Column(name = "image", columnDefinition = "BLOB")
	private String image;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//	@JoinColumn(name = "product_id",  referencedColumnName = "product_id")
	private List<ProductRates> productRates;

	@OneToOne
	@JoinColumn(name = "prod_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Category productCategory;

	@OneToOne
	@JoinColumn(name = "uom", referencedColumnName = "id", insertable = false, updatable = false)
	private Category uomCategory;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "prod_id", referencedColumnName = "product_id")
	private List<ProductStock> productStock;
}
