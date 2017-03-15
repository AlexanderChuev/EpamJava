package com.chuyeu.training.myapp.dao;

import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductDao {

	Product getId(Integer id);
	void insert(Product product);
	void update(Product product);
	
}
