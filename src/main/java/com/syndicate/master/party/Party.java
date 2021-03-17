package com.syndicate.master.party;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.syndicate.master.state.StateGST;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "party")
public class Party implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "party_type_id")
	private Integer partyTypeId;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "addr1")
	private String addr1;
	@Column(name = "addr2")
	private String addr2;
	@Column(name = "city")
	private String city;
	@Column(name = "Pincode")
	private Integer pinCode;
	@Column(name = "gstin")
	private String gstin;
	@Column(name = "state_id")
	private Integer stateId;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "ob_amount")
	private Float obAmount;
	@Column(name = "create_date")
	private LocalDate createDate;
	@Column(name = "updated_date")
	private LocalDate updatedDate;
	@Column(name = "create_by_user")
	private Integer createByUser;
	@Column(name = "updated_by")
	private String updatedByUser;
	@Column(name = "inactive")
	private boolean inactive;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "party_type_id", referencedColumnName = "id", insertable = false, updatable = false)
	private PartyType partyType;

	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id", insertable = false, updatable = false)
	private StateGST stateGst;
	
}
