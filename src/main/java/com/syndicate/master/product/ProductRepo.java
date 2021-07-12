package com.syndicate.master.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	@Query("FROM com.syndicate.master.product.Product p WHERE p.name LIKE %:search% ")
	Optional<List<Product>> findAll(String search);

	Optional<Product> findByName(String name);
}
