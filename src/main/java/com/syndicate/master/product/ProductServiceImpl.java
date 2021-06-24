package com.syndicate.master.product;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
import com.syndicate.master.all.categories.CategoryDTO;
import com.syndicate.master.all.categories.ICategoryService;

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

	@Autowired
	ICategoryService<CategoryDTO> categoryService;

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public String save(ProductDTO product) {
		Product p = convertToEntity.map(product, Product.class);
		p = productRepo.save(p);
		ProductRates pr = convertToEntity.map(product.getProductRates(), ProductRates.class);
		pr.setProductId(p.getId());
		pr.setRate(0.0f);
		productRatesRepo.save(pr);
		return "Product saved";
	}

	@Override
	@Cacheable(value = "productCache")
	@Caching(evict = { @CacheEvict(value = "productCache", allEntries = true) })
	public Optional<List<ProductDTO>> findAll(Long storeId) {
		List<Product> p = (List<Product>) productRepo.findAll();
		Comparator<ProductRates> myComparator = (arg1, arg2) -> {
			if (arg2.getWef().isBefore(arg1.getWef()))
				return -1;
			else if (arg2.getWef().isEqual(arg1.getWef()))
				return 0;
			else
				return 1;
		};

		p.stream().map(handler -> {
			List<ProductRates> sortedRates = handler.getProductRates().stream().sorted(myComparator)
					.collect(Collectors.toList());
			handler.setProductRates(sortedRates);
			return sortedRates;
		}).collect(Collectors.toList());

//		p.stream().map(f -> {
//			List<ProductStock> s = f.getProductStock().stream().filter(o -> {
//				return o.getStoreId() == storeId ? true : false;
//			}).collect(Collectors.toList());
//			f.setProductStock(s);
//			return f;
//		}).collect(Collectors.toList());

		p.stream().map(handler -> handler.getProductStock()).flatMap(f -> f.stream()).filter(f -> {
			return f.getStoreId() == storeId ? true : false;
		}).collect(Collectors.toList());

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
	@Transactional(rollbackOn = { Exception.class })
	public Optional<ProductDTO> update(ProductDTO productDTO) {
		ProductRates pr = new ProductRates();
		Product p = productRepo.save(convertToEntity.map(productDTO, Product.class));
		pr.setRate(0.0f);
		pr.setWef(LocalDate.now());
		pr.setProductId(p.getId());
		productRatesRepo.save(pr);
		return Optional.of(convertToDto.mapList(p, ProductDTO.class));
	}

	@Override
	public Map<String, Object> findAll(String search, int pageNumber, int pageSize, int totalRecords) {
		ConcurrentHashMap<String, Object> al = new ConcurrentHashMap<String, Object>();
		if (pageNumber > 0)
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
		al.put("page", pageNumber);
		al.put("list", list);
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

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public Optional<ProductRatesDTO> update(ProductRatesDTO productRate) {
		ProductRates pr = null;
		Optional<List<ProductRates>> prOpt = productRatesRepo
				.findAllByProductIdAndStoreIdOrderByWefDesc(productRate.getProductId(), new Long(1));
		if (prOpt.isPresent()) {
			pr = prOpt.get().get(0);
			pr.setRate(productRate.getRate());
			pr.setGst(productRate.getGst());
			pr.setCgst(productRate.getCgst());
			pr.setSgst(productRate.getSgst());
			pr.setWef(productRate.getWef());
			productRatesRepo.save(pr);
		}
		return Optional.of(convertToDto.mapList(pr, ProductRatesDTO.class));
	}

	@Override
	public Long update(List<UploadProductDTO> uploadProductDTO) {
		System.out.println("ProductList\n" + uploadProductDTO);
		List<CategoryDTO> uomList = categoryService.findAll("UOM_CATEGORY", CategoryDTO.class);

		System.out.println(uomList);
		uploadProductDTO.forEach(o -> {
			Optional<Product> opt = productRepo.findByHsnCode(o.getHsncode());
			System.out.println(opt);
			if (opt.isPresent()) {
				Product p = opt.get();
				p.setName(o.getProduct());
				p.setIsDeleted(Boolean.FALSE);
				p.setUom(uomId(uomList, o));
				productRepo.save(p);
			} else {

			}
		});
		return 100l;
	}

	private Long uomId(List<CategoryDTO> uomList, UploadProductDTO o) {
		CategoryDTO uom = uomList.stream().filter(f -> {
			return f.getName().equals(o.getUom());
		}).findFirst().get();
		if (uom == null) {
			return 0l;
		} else {
			return uom.getId();
		}
	}

}
