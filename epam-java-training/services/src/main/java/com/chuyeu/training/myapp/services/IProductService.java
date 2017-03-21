package com.chuyeu.training.myapp.services;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductService {

	List<Product> getAll();
	
	Product get(Integer id);
	
	Product insert(Product product);
	
	Product update(Product product);
	
	void delete (Integer id);
	
}
