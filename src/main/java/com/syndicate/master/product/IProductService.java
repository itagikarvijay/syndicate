package com.syndicate.master.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService {
	
	String save(ProductDTO product);
	
	Optional<List<ProductDTO>> findAll(Long storeId);
	Map<String,Object> findAll(String search,int pageNo, int pageSize, int totalRecords);
	Optional<List<ProductDTO>> findAll(int pageNo, int pageSize);
	
	Optional<ProductDTO> update(ProductDTO product);
	Long update(List<UploadProductDTO> productDTO);
	
	Optional<ProductRatesDTO> update(ProductRatesDTO productRate);
	Long searchProductCount(String search);
}
