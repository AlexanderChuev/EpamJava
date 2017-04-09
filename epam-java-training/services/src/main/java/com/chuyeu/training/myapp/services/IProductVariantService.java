package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.entity.ProductVariantEntity;

public interface IProductVariantService {

	List<ProductVariantEntity>getAllByProduct(Integer productId);
	
	ProductVariantEntity getProductVariant(Integer id);
	
	@Transactional
	ProductVariant saveOrUpdate(ProductVariant productVariant);
	
	@Transactional
	void delete (Integer id);
}
