package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.ProductVariant;

public interface IProductVariantDao extends AbstractDao<ProductVariant> {

	List<ProductVariant> getAllByProduct(Integer productId);

	Integer add(ProductVariant productVariant);

	void update(ProductVariant productVariant);
}
