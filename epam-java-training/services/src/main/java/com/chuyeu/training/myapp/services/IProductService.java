package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductService {

	List<Product> getAll();
	
	Product get(Integer id);
	
	@Transactional
	Product saveOrUpdate(Product product);
	
	@Transactional
	void delete (Integer id);
	
}
