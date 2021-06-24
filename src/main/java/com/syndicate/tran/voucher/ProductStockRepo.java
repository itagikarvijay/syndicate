package com.syndicate.tran.voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepo extends JpaRepository<ProductStock, Long> {

	ProductStock findByProdIdAndStoreId(Long prodId,Long storeId);
}
