package com.syndicate.master.all.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syndicate.master.product.UomCategory;

@Repository
public interface UomCategoryRepo extends JpaRepository<UomCategory, Long> {

}
