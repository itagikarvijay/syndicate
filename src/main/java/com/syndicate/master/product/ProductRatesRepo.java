package com.syndicate.master.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRatesRepo extends JpaRepository<ProductRates,Long> {

}
