package com.syndicate.master.product;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.syndicate.master.all.categories.Category;

@Entity
@DiscriminatorValue("UOM_CATEGORY")
public class UomCategory extends Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
