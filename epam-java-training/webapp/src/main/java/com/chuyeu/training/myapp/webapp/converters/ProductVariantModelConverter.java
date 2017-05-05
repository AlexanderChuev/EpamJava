package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.webapp.models.ProductVariantModel;

public class ProductVariantModelConverter implements Converter<ProductVariantModel, ProductVariant>{

	@Override
	public ProductVariant convert(ProductVariantModel productVariantModel) {
		ProductVariant productVariant = new ProductVariant();
		productVariant.setProductId(productVariantModel.getProductId());
		productVariant.setAvailableQuantity(productVariantModel.getAvailableQuantity());
		productVariant.setPriceInfluence(productVariantModel.getPriceInfluence());
		return productVariant;
	}

}
