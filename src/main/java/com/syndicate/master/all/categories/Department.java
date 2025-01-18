package com.syndicate.master.all.categories;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@DiscriminatorValue("DEPT_TYPE")
public class Department extends Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}