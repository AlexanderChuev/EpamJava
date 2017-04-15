package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Inject
	private IProductDao productDao;

	@Override
	public List<Product> getAll(ProductFilter productFilter) {
		return productDao.getAll(productFilter);
	}

	@Override
	public Product get(Integer id) {
		return productDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
	}

	@Override
	public Integer add(Product product) {
		return productDao.add(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public Integer getProductQuantity() {
		return productDao.getProductQuantity();
	}

}
