/**
 * 
 */
package com.syndicate.tran.voucher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

/**
 * @author Vijay
 *
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voucher_id")
	private Long id;
	@Column(name = "voucher_date")
	private LocalDate voucherDate;
	@Column(name = "voucher_type_id")
	private Long voucherTypeId;
	@Column(name = "store_id")
	private Long storeId;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "party_id")
	private Long partyId;
	@Column(name = "cr_amount")
	private Float voucherAmountCredit;
	@Column(name = "dr_amount")
	private Float voucherAmountDebit;
	@Column(name = "ship_to")
	private Long shipTo;
	@Column(name = "gstin")
	private String gstin;
	@Column(name = "shipper_id")
	private Long shipperId;
	@Column(name = "vehicle_no")
	private String vehicleNo;
	@Column(name = "place_of_supply")
	private String placeOfSupply;
	@Column(name = "itds")
	private Float itds;
	@Column(name = "itcs")
	private Float itcs;
	@Column(name = "cr_dr")
	private Integer crDr;
	@Column(name = "type")
	private String type;
	@Column(name = "type_number")
	private String typeNumber;
	
	@OneToMany(mappedBy="voucher", cascade = CascadeType.ALL)
    private List<VoucherDetails> voucherDetails;

	@OneToOne
	@JoinColumn(name = "voucher_type_id", referencedColumnName = "id", insertable = false, updatable = false)
	private VoucherType voucherType;
}
