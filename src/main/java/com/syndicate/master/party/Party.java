package com.syndicate.master.party;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.syndicate.master.all.categories.Category;
import com.syndicate.master.state.StateGst;

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
	private Long partyTypeId;
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
	private Long stateId;
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
//	@Column(name = "isDeleted")
//	private boolean isDeleted;	

	@OneToOne
	@JoinColumn(name = "party_type_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Category partyType;

	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id", insertable = false, updatable = false)
	private StateGst stateGst;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "party_id")	
	List<ShippingAddress> shippingAddress;

}
