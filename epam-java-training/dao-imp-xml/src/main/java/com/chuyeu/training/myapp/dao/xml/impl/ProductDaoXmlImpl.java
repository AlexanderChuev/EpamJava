package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;

@Repository
public class ProductDaoXmlImpl implements IProductDao {

	@Override
	public Product get(Integer id) {
		return null;
	}

	@Override
	public Integer add(Product product) {
		return null;
	}

	@Override
	public void update(Product product) {
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public List<Product> getAll(ProductFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getProductQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

}
