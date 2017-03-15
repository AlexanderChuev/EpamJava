package com.chuyeu.training.myapp.dao.impl;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IProductDao;
import com.chuyeu.training.myapp.datamodel.Product;

@Repository
public class ProductDaoImpl implements IProductDao{

	@Override
	public Product getId(Integer id) {
		Product product = new Product();
		product.setId(1);
		return product;
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	
}
