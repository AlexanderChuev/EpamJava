package com.chuyeu.training.myapp.webapp.converters;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.util.Language;
import com.chuyeu.training.myapp.services.util.LanguageWrapper;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

public class ProductConverter implements Converter<Product, ProductModel>{
	
	@Inject
	private LanguageWrapper wrapper;

	@Override
	public ProductModel convert(Product product) {
		
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setActive(product.getActive());
		productModel.setBasePrice(product.getBasePrice());
		
		if(wrapper.getLanguage()==null){
			wrapper.setLanguage(Language.RU);
		}
		switch(wrapper.getLanguage()){
		
		case EN: 
			productModel.setNameEn(product.getNameEn());
			productModel.setDescriptionEn(product.getDescriptionEn());
			break;
			
		default:
			productModel.setNameRu(product.getNameRu());
			productModel.setDescriptionRu(product.getDescriptionRu());
		}
		
		return productModel;
	}

}
