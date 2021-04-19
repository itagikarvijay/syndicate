package com.syndicate.master.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService {
	
	String save(ProductDTO product);
	
	Optional<List<ProductDTO>> findAll();
	Map<String,Object> findAll(String search,int pageNo, int pageSize, int totalRecords);
	Optional<List<ProductDTO>> findAll(int pageNo, int pageSize);
	
	Optional<ProductDTO> update(ProductDTO product);

	Long searchProductCount(String search);
}
