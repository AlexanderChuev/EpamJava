package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.webapp.models.ProductVariantModel;

public class ProductVariantConverter implements Converter<ProductVariant, ProductVariantModel>{

	@Override
	public ProductVariantModel convert(ProductVariant productVariant) {
		ProductVariantModel productVariantModel = new ProductVariantModel();
		productVariantModel.setId(productVariant.getId());
		productVariantModel.setAvailableQuantity(productVariant.getAvailableQuantity());
		productVariantModel.setPriceInfluence(productVariant.getPriceInfluence());
		return productVariantModel;
	}

}
