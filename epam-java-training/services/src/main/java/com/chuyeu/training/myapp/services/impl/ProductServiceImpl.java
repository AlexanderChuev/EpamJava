package com.chuyeu.training.myapp.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IProductDao;
import com.chuyeu.training.myapp.dao.impl.ProductDaoImpl;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	
	@Inject
	private IProductDao productDao;
	
	public ProductServiceImpl() {
		super();
		productDao = new ProductDaoImpl();
	}


	@Override
	public Product getId(Integer id) {
		return productDao.getId(id);
	}

	@Override
	public void save(Product product) {
	}
}
