package com.syndicate.master.product;

import java.io.Serializable;



import com.syndicate.master.all.categories.Category;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("UOM_CATEGORY")
public class UomCategory extends Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
