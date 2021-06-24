package com.syndicate.master.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRatesRepo extends JpaRepository<ProductRates,Long> {
	
	Optional<List<ProductRates>> findAllByProductIdAndStoreIdOrderByWefDesc(Long productId, Long storeId);

}
