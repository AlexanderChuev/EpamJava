package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.ProductVariant;

public interface IProductVariantDao {

	List<ProductVariant> getAllByProduct(Integer productId);

	List<ProductVariant> getAll();

	ProductVariant get(Integer id);

	ProductVariant insert(ProductVariant productVariant);

	ProductVariant update (ProductVariant productVariant);

	void delete(Integer id);
}
