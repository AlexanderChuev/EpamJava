package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

public class ProductConverter implements Converter<Product, ProductModel>{

	@Override
	public ProductModel convert(Product product) {
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setName(product.getName());
		productModel.setDescription(product.getDescription());
		productModel.setActive(product.getActive());
		productModel.setBasePrice(product.getBasePrice());
		return productModel;
	}

}
