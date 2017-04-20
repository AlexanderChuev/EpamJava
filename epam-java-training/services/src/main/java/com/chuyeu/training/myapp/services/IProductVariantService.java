package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.ProductVariant;

public interface IProductVariantService {

	List<ProductVariant>getAllByProduct(Integer productId);
	
	ProductVariant getProductVariant(Integer id);
	
	@Transactional
	Integer saveOrUpdate(ProductVariant productVariant);
	
	@Transactional
	void delete (Integer id);
}
