package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IProductVariantService;

@Service
public class ProductVariantServiceImpl implements IProductVariantService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductVariantServiceImpl.class);

	@Inject
	private IProductVariantDao productVariantDao;

	@Override
	public List<ProductVariant> getAllByProduct(Integer productId) throws EmptyResultDataAccessException {
		return productVariantDao.getAllByProduct(productId);
	}

	@Override
	public ProductVariant getProductVariant(Integer id) {
		return productVariantDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		productVariantDao.delete(id);
		LOGGER.info("Delete productVariant with id " + id);
	}

	@Override
	public Integer save(ProductVariant productVariant) {
		LOGGER.info("Insert productVariant with product.id={}. priceInfluence={}. availableQuantity={}",
				productVariant.getProductId(), productVariant.getPriceInfluence(),
				productVariant.getAvailableQuantity());
		return productVariantDao.add(productVariant);
	}

	@Override
	public void update(ProductVariant productVariant) {
		LOGGER.info("Update productVariant with product.id={}. priceInfluence={}. availableQuantity={}",
				productVariant.getProductId(), productVariant.getPriceInfluence(),
				productVariant.getAvailableQuantity());
		productVariantDao.update(productVariant);
	}

}
