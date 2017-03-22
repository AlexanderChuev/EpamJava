package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.chuyeu.training.myapp.dao.IProductDao;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	
	@Inject
	private IProductDao productDao;

	@Override
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@Override
	public Product get(Integer id) {
		return productDao.get(id);
	}

	@Override
	public Product insert(Product product) {
		return productDao.insert(product);
	}

	@Override
	public Product update(Product product) {
		return productDao.update(product);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
	}
	

}
