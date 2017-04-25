package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	private  final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
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
		LOGGER.info("Insert new product name={}. description={}. basePrice={}. active={}", 
				product.getName(), product.getDescription(), product.getBasePrice(), product.getActive());
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
