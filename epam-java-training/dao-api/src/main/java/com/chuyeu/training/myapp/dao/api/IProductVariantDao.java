package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.ProductVariant;

public interface IProductVariantDao {

	List<ProductVariant> getAllByProduct(Integer productId);

	ProductVariant get(Integer id);

	Integer add(ProductVariant productVariant);

	void update (ProductVariant productVariant);

	void delete(Integer id);
}
