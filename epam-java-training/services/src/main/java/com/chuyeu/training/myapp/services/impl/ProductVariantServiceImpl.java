package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IProductVariantDao;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IProductVariantService;

@Service
public class ProductVariantServiceImpl implements IProductVariantService {

	@Inject
	private IProductVariantDao productVariantDao;

	@Override
	public List<ProductVariant> getAll() {
		return productVariantDao.getAll();
	}

	@Override
	public ProductVariant get(Integer id) {
		return productVariantDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		productVariantDao.delete(id);
	}

	@Override
	public ProductVariant saveOrUpdate(ProductVariant entity) {
		if (entity.getId() == null) {
			return productVariantDao.insert(entity);
		} else {
			return productVariantDao.update(entity);
		}
	}

}
