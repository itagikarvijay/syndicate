package com.syndicate.master.party;

import java.time.LocalDate;
import java.util.List;

import com.syndicate.master.all.categories.Category;
import com.syndicate.master.state.StateGst;

import lombok.Data;

@Data
public class PartyDTO {
	
	private Long id;
	private Integer partyTypeId;
	private String name;
	private String email;
	private String mobileNo;
	private String addr1;
	private String addr2;
	private String city;
	private String updatedByUser;
	private String gstin;
	
	private Integer pinCode;
	private Integer createByUser;
	private Integer stateId;
	
	private Float obAmount;
	
	private LocalDate startDate;
	private LocalDate createDate;
	private LocalDate updatedDate;
	
	
	private boolean inactive;

	private Category partyType;

	private StateGst stateGst;
	
	List<ShippingAddress> shippingAddress;
}
