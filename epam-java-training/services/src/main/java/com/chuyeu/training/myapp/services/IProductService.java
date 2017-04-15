package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductService {

	List<Product> getAll(ProductFilter productFilter);
	
	Product get(Integer id);
	
	Integer getProductQuantity();
	
	@Transactional
	Integer add(Product product);
	
	@Transactional
	void update(Product product);
	
	@Transactional
	void delete (Integer id);
	
}
