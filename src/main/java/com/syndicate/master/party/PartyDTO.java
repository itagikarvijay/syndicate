package com.syndicate.master.party;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PartyDTO {
	private Long id;
//	private Integer partyTypeId;
	private String name;
	private String email;
	private String mobileNo;
	private String addr1;
	private String addr2;
	private String city;
	private Integer pinCode;
	private String gstin;
	private Integer stateId;
	private LocalDate startDate;
	private Float obAmount;
	private LocalDate createDate;
	private String createByUser;
	private String updatedByUser;
	private boolean inactive;
}
