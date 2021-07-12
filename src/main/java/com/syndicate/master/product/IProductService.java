package com.syndicate.master.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.syndicate.conversion.utility.WrapperClz;

public interface IProductService {
	
	String save(ProductDTO product);
	
	Optional<List<ProductDTO>> findAll(Long storeId);
	Map<String,Object> findAll(String search,int pageNo, int pageSize, int totalRecords);
	Optional<List<ProductDTO>> findAll(int pageNo, int pageSize);
	
	Optional<ProductDTO> update(ProductDTO product);
	Long update(UploadProductDTO list);
	
	Optional<ProductRatesDTO> update(ProductRatesDTO productRate);
	Long searchProductCount(String search);
}
