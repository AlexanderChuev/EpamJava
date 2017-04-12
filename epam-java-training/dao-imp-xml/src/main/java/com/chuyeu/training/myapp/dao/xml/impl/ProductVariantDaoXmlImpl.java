package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

@Repository
public class ProductVariantDaoXmlImpl implements IProductVariantDao {


	@Override
	public List<ProductVariant> getAll() {
		return null;
	}

	// +++
	@Override
	public ProductVariant get(Integer id) {
		return null;
	}

	@Override
	public ProductVariant insert(ProductVariant productVariant) {
		return null;
	}

	@Override
	public ProductVariant update(ProductVariant product_variant) {
		return null;
	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public List<ProductVariant> getAllByProduct(Integer productId) {
		return null;
	}

}
