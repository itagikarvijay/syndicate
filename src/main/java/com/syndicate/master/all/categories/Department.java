package com.syndicate.master.all.categories;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("DEPT_TYPE")
public class Department extends Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}