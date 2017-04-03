package com.chuyeu.training.myapp.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IAttributeDao;
import com.chuyeu.training.myapp.dao.IProductDao;
import com.chuyeu.training.myapp.dao.IProductVariantDao;
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

		Product commonProduct = productDao.get(allByProduct.get(0).getProductId());

		for (ProductVariant productVariant : allByProduct) {
			List<Attribute> attributes = attributeDao.getProductVariantAttributes(productVariant.getId());
			productVariantEntities.add(new ProductVariantEntity(productVariant, commonProduct, attributes));
		}

		return productVariantEntities;
	}

	@Override
	public ProductVariantEntity getProductVariant(Integer id) {

		ProductVariant pv = productVariantDao.get(id);
		Product product = productDao.get(pv.getProductId());
		List<Attribute> attributes = attributeDao.getProductVariantAttributes(id);

		return new ProductVariantEntity(pv, product, attributes);
	}

	@Override
	public ProductVariant saveOrUpdate(ProductVariant productVariant) {
		if (productVariant.getId() == null) {
			return productVariantDao.insert(productVariant);
		} else {
			return productVariantDao.update(productVariant);
		}
	}
	
	@Override
	public void delete(Integer id) {
		productVariantDao.delete(id);
	}

}
