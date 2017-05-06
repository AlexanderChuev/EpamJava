package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductDao extends AbstractDao<Product>{

	List<Product> getAll(CommonFilter commonFilter);

	Integer add(Product product);

	void update(Product product);
	
	Integer getProductQuantity();

}
