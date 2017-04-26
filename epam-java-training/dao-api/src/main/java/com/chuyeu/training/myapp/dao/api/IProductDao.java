package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Product;

public interface IProductDao {

	List<Product> getAll(CommonFilter commonFilter);

	Product get(Integer id);

	Integer add(Product product);

	void update(Product product);

	void delete(Integer id);
	
	Integer getProductQuantity();

}
