package com.syndicate.master.all.categories;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PARTY_TYPE")
public class PartyType extends Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
