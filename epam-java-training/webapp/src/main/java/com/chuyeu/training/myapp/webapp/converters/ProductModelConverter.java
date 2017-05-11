package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

public class ProductModelConverter implements Converter<ProductModel, Product> {

	@Override
	public Product convert(ProductModel model) {
		Product product = new Product();
		product.setActive(model.getActive());
		product.setBasePrice(model.getBasePrice());
		product.setNameEn(model.getNameEn());
		product.setDescriptionEn(model.getDescriptionEn());
		product.setNameRu(model.getNameRu());
		product.setDescriptionRu(model.getDescriptionRu());

		return product;
	}

}
