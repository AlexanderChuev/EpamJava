package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

public class ProductModelConverter implements Converter<ProductModel, Product> {

	@Override
	public Product convert(ProductModel model) {
		Product product = new Product();
		product.setName(model.getName());
		product.setDescription(model.getDescription());
		product.setActive(model.getActive());
		product.setBasePrice(model.getBasePrice());
		return product;
	}

}
