package com.syndicate.master.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.syndicate.conversion.utility.ConvertToDto;
import com.syndicate.conversion.utility.ConvertToEntity;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	ProductRatesRepo productRatesRepo;

	@Autowired
	ConvertToEntity convertToEntity;

	@Autowired
	ConvertToDto convertToDto;

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public String save(ProductDTO product) {
		Product p = convertToEntity.map(product, Product.class);
		p = productRepo.save(p);
		ProductRates pr = convertToEntity.map(product.getProductRates(), ProductRates.class);
		pr.setProductId(p.getId());
		productRatesRepo.save(pr);
		return "Product saved";
	}

	@Override
	@Cacheable(value = "productCache")
	@Caching(evict = { @CacheEvict(value = "productCache", allEntries = true) })
	public Optional<List<ProductDTO>> findAll() {
		List<Product> p = (List<Product>) productRepo.findAll();
		return Optional.of(convertToDto.mapList(p, ProductDTO.class));
	}

	@Override
	@Cacheable(value = "productCache")
	@Caching(evict = { @CacheEvict(value = "productCache", allEntries = true) })
	public Optional<List<ProductDTO>> findAll(int pageNo, int pageSize) {
		Pageable firstPageWithTwoElements = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
		Page<Product> page = productRepo.findAll(firstPageWithTwoElements);
		return Optional.of(convertToDto.mapList(page, ProductDTO.class));
	}

	@Override
	public Optional<ProductDTO> update(ProductDTO productDTO) {
		Product p = productRepo.save(convertToEntity.map(productDTO, Product.class));
		return Optional.of(convertToDto.mapList(p, ProductDTO.class));
	}

	@Override
	public Map findAll(String search, int pageNumber, int pageSize, int totalRecords) {
		ConcurrentHashMap<String,Object> al = new ConcurrentHashMap<String,Object>();
		if(pageNumber > 0)
			pageNumber = pageNumber * 5;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

		Root<Product> from = criteriaQuery.from(Product.class);
		CriteriaQuery<Product> select = criteriaQuery.select(from)
				.where(criteriaBuilder.like(from.get("name"), "%" + search + "%"));
		criteriaQuery.orderBy(criteriaBuilder.asc(from.get("name")));

		TypedQuery<Product> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult(pageNumber);
		typedQuery.setMaxResults(pageSize);
		System.out.println("Current page: " + typedQuery.getResultList());

		List<ProductDTO> p = convertToDto.mapList(typedQuery.getResultList(), ProductDTO.class);
		
		Optional<List<ProductDTO>> list = Optional.of(p);
		al.put("page",pageNumber);
		al.put("list",list);
		return al;
	}

	@Override
	public Long searchProductCount(String search) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Product> iRoot = countQuery.from(Product.class);
		countQuery.select(criteriaBuilder.count(iRoot));
		countQuery.where(criteriaBuilder.like(iRoot.get("name"), "%" + search + "%"));
		Long count = entityManager.createQuery(countQuery).getSingleResult();
		return count;
	}

}
