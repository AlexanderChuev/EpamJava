package com.chuyeu.training.myapp.services;

import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductService {

	Product getId(Integer id);
	void save(Product product);
	
}
