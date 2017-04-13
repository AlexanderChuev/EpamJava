package com.chuyeu.training.myapp.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.entity.ProductVariantEntity;

@Service
public class ProductVariantServiceImpl implements IProductVariantService {

	@Inject
	private IProductVariantDao productVariantDao;

	@Inject
	private IProductDao productDao;

	@Inject
	private IAttributeDao attributeDao;

	@Override
	public List<ProductVariantEntity> getAllByProduct(Integer productId) {

		List<ProductVariant> allByProduct = productVariantDao.getAllByProduct(productId);
		List<ProductVariantEntity> productVariantEntities = new ArrayList<>();

		Product currentProduct = productDao.get(allByProduct.get(0).getProductId());

		for (ProductVariant productVariant : allByProduct) {
			List<Attribute> attributes = attributeDao.getProductVariantAttributes(productVariant.getId());
			productVariantEntities.add(new ProductVariantEntity(productVariant, currentProduct, attributes));
		}

		return productVariantEntities;
	}

	@Override
	public ProductVariantEntity getProductVariant(Integer id) {

		ProductVariant productVariant = productVariantDao.get(id);
		Product product = productDao.get(productVariant.getProductId());
		List<Attribute> attributes = attributeDao.getProductVariantAttributes(id);

		return new ProductVariantEntity(productVariant, product, attributes);
	}

	@Override
	public void saveOrUpdate(ProductVariant productVariant) {
		if (productVariant.getId() == null) {
			productVariantDao.add(productVariant);
		} else {
			productVariantDao.update(productVariant);
		}
	}
	
	@Override
	public void delete(Integer id) {
		productVariantDao.delete(id);
	}

}
