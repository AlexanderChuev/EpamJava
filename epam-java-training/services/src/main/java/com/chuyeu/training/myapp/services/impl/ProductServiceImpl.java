package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Inject
	private IProductDao productDao;

	@Override
	public List<Product> getAll(CommonFilter commonFilter) {
		return productDao.getAll(commonFilter);
	}

	@Override
	public Product get(Integer id) {
		return productDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
		LOGGER.info("Delete product with id " + id);
	}

	@Override
	public Integer add(Product product) {
		LOGGER.info("Insert product with nameRu={} descriptionRu={} basePrice={} active={} nameEn={} descriptionEn={}", product.getNameRu(),
				product.getDescriptionRu(), product.getBasePrice(), product.getActive(), product.getNameEn(),
				product.getDescriptionEn());
		return productDao.add(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
		LOGGER.info("Update product with id={} nameRu={} descriptionRu={} basePrice={} active={} nameEn={} descriptionEn={}", product.getId(),
				product.getNameRu(), product.getDescriptionRu(), product.getBasePrice(), product.getActive(), product.getNameEn(),
				product.getDescriptionEn());
	}

	@Override
	public Integer getProductQuantity() {
		return productDao.getProductQuantity();
	}

}
