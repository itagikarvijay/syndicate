package com.syndicate.master.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

	@Autowired
	IProductService iProductService;

	@PostMapping("/save")
	public String save(@RequestBody ProductDTO productDTO) {
		return iProductService.save(productDTO);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<ProductDTO>> findAll() {
		Optional<List<ProductDTO>> productList = iProductService.findAll();
		return new ResponseEntity<List<ProductDTO>>(productList.get(), HttpStatus.OK);
	}
	
	
	@GetMapping("/searchProductCount")
	public Long findAll(@RequestParam("search") String search) {
		return iProductService.searchProductCount(search);
	}

	@GetMapping("/findAllWithPagination")
	public ResponseEntity<List<ProductDTO>> findAll(@RequestParam("page") String paramPage,
			@RequestParam("pageSize") String paramPageSize) {
		int page = Integer.valueOf(paramPage);
		int pageSize = Integer.valueOf(paramPageSize);
		Optional<List<ProductDTO>> productList = iProductService.findAll(page, pageSize);
		return new ResponseEntity<List<ProductDTO>>(productList.get(), HttpStatus.OK);
	}

	@GetMapping("/searchAllWithPagination")
	public ResponseEntity<Map<String,Object>> findAll(@RequestParam("search") String search,
			@RequestParam("page") String paramPage, @RequestParam("pageSize") String paramPageSize, @RequestParam("totalRecords") String paramTotalRecords) {
		int page = Integer.valueOf(paramPage);
		int pageSize = Integer.valueOf(paramPageSize);
		int totalRecords = Integer.valueOf(paramTotalRecords);
		Map<String, Object> productList = iProductService.findAll(search, page, pageSize, totalRecords);
		return new ResponseEntity<Map<String,Object>>(productList, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
		Optional<ProductDTO> productOPT = iProductService.update(product);
		return new ResponseEntity<ProductDTO>(productOPT.get(), HttpStatus.OK);
	}

	@PatchMapping(path = "/delete", consumes = "application/json-patch+json")
	public ResponseEntity<ProductDTO> delete(@RequestBody ProductDTO product) {
		Optional<ProductDTO> productOPT = iProductService.update(product);
		return new ResponseEntity<ProductDTO>(productOPT.get(), HttpStatus.OK);
	}
}
