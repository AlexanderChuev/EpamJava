package com.chuyeu.training.myapp.services;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.entity.ProductVariantEntity;

public interface IProductVariantService {

	List<ProductVariantEntity>getAllByProduct(Integer productId);
	
	ProductVariantEntity getProductVariant(Integer id);
	
	ProductVariant saveOrUpdate(ProductVariant productVariant);
	
	void delete (Integer id);
}
