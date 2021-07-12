package com.syndicate.master.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
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
import com.syndicate.master.all.categories.Category;
import com.syndicate.master.all.categories.CategoryDTO;
import com.syndicate.master.all.categories.ICategoryService;
import com.syndicate.tran.voucher.ProductStock;

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
	//	pr.setProductId(p.getId());
		pr.setProduct(p);
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
		Product p = productRepo.save(convertToEntity.map(productDTO, Product.class));
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
//		System.out.println("Current page: " + typedQuery.getResultList());

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
		ProductRates pr = new ProductRates();
//		Optional<List<ProductRates>> prOpt = productRatesRepo
//				.findAllByProductIdAndStoreIdOrderByWefDesc(productRate.getProductId(), new Long(1));
//		if (prOpt.is) {
			pr.setRate(productRate.getRate());
			pr.setGst(productRate.getGst());
			pr.setCgst(productRate.getCgst());
			pr.setSgst(productRate.getSgst());
			pr.setWef(productRate.getWef());
			productRatesRepo.save(pr);
//		}
		return Optional.of(convertToDto.mapList(pr, ProductRatesDTO.class));
	}

	@Override
//	public Long update(WrapperClz list) {
	public Long update(UploadProductDTO o) {
//		System.out.println("ProductList\n" + list.getSuccessList());
		Product p = null;
		;
		try {
			List<CategoryDTO> categoryIdList = categoryService.findAll("", CategoryDTO.class);
			Optional<Product> opt = productRepo.findByName(o.getProduct());
//		 list.getSuccessList().forEach(o -> {
//			Optional<Product> opt = productRepo.findByHsnCode(o.getHsncode());
//			System.out.println(opt);
//			if (opt.isPresent()) {
//				Product p = opt.get();
//				p.setName(o.getProduct());
//				p.setIsDeleted(Boolean.FALSE);
//				p.setUom(getCategoryId(categoryIdList, o, "UOM_TYPE"));
//				p.setProdCategryId(getCategoryId(categoryIdList, o, "CATEGORY_TYPE"));
//				p.setProductRates(updateQuantityForProduct(p.getId(), o, list.getStoreId()));
//				productRepo.save(p);
//			} else {
			// new product
			if (opt.isPresent()) {
				p = opt.get();
			} else {
				p = new Product();
			}
			p.setName(o.getProduct());
			p.setIsDeleted(Boolean.FALSE);
			p.setUom(getCategoryId(categoryIdList, o, "UOM_TYPE"));
			p.setProdCategryId(getCategoryId(categoryIdList, o, "CATEGORY_TYPE"));
			p.setIsDeleted(Boolean.FALSE);
			p.setInactive(Boolean.FALSE);
			p.setService(Boolean.FALSE);
			p.setHsnCode(o.getHsncode());
			p.setProductRates(updateRateForProduct(p, o, 1l));
			p.setProductStock(updateQuantity(p, o.getQty()));
			productRepo.save(p);
		} catch (Exception e) {
			System.out.println("Error occured while updating...");
			e.printStackTrace();
		}

//			}
//		});
		return 100l;

	}

	private List<ProductStock> updateQuantity(Product p, String qty) {
		
		List<ProductStock> l = new ArrayList<ProductStock>();
		ProductStock ps = p.getProductStock() == null ? new ProductStock() : p.getProductStock().get(0);
		ps.setCloStock(ps.getCloStock() + Float.parseFloat(qty));
		ps.setProdId(p.getId());
		l.add(ps);
		return l;
	}

	private List<ProductRates> updateRateForProduct(Product productId, UploadProductDTO o, Long storeId) {
		List<ProductRates> list = new ArrayList<ProductRates>();
//		Optional<ProductRates> opt = productRatesRepo.findById(productId);
//		if (opt.isEmpty()) {
//
//		} else {
//		System.out.println("productId "+productId);
		ProductRates pr = new ProductRates(); // opt.get();
		pr.setRate(Float.parseFloat(o.getRate()));
		pr.setProduct(productId);
		pr.setGst(Float.parseFloat(o.getGst()));
		pr.setSgst(Float.parseFloat(o.getSgst()));
		pr.setCgst(Float.parseFloat(o.getCgst()));
		pr.setStoreId(storeId);
		pr.setWef(LocalDate.now());
		list.add(pr);
//		}
//		System.out.println("list "+list);
		return list;
	}

	private Long getCategoryId(List<CategoryDTO> categoryIdList, UploadProductDTO o, String categoryType) {
		Optional<CategoryDTO> uom = null;
		Category c = null;

		switch (categoryType) {
		case "UOM_TYPE":
			uom = categoryIdList.stream().filter(f -> {
				return f.getName().equals(o.getUom());
			}).findFirst();
			break;
		case "CATEGORY_TYPE":
			uom = categoryIdList.stream().filter(f -> {
				return f.getName().equals(o.getCategory());
			}).findFirst();
			break;
		default:
			break;
		}
		if (uom.isEmpty()) {
			switch (categoryType) {
			case "UOM_TYPE":
				c = new UomCategory();
				c.setName(o.getUom());
			case "CATEGORY_TYPE":
				c = new ProductCategory();
				c.setName(o.getCategory());
			default:
				break;
			}
			return categoryService.save(c);
		} else {
//			System.out.println(uom.get().getId());
			return uom.get().getId();
		}
	}

}
