package com.syndicate.master.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

}
