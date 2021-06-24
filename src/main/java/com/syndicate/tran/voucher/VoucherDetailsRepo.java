package com.syndicate.tran.voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherDetailsRepo extends JpaRepository<VoucherDetails, Long> {

	@Query("SELECT vd.cbQty " + "FROM VoucherDetails as vd " + "WHERE vd.id = (SELECT MAX(s.id) FROM VoucherDetails s WHERE s.productId = :productId )")
	public Float getOb(Long productId);
}
