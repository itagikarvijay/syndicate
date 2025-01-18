package com.syndicate.master.party;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "ship_addr")
public class ShippingAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "party_id")
	private Long partyId;
	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "addr1")
	private String addr1;
	@Column(name = "addr2")
	private String addr2;
	@Column(name = "city")
	private String city;
	@Column(name = "pincode")
	private String pinCode;
	@Column(name = "state_id")
	private Long stateId;
	@Column(name = "deleted")
	private boolean isDeleted;

	
}
