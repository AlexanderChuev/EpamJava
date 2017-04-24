package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.ProductVariantModel;

public class AbstractConroller {

	public ProductModel entity2model(Product product) {
		ProductModel model = new ProductModel();
		model.setName(product.getName());
		model.setDescription(product.getDescription());
		model.setActive(product.getActive());
		model.setBasePrice(product.getBasePrice());
		return model;
	}

	public Product model2entity(ProductModel model) {
		Product product = new Product();
		product.setName(model.getName());
		product.setDescription(model.getDescription());
		product.setActive(model.getActive());
		product.setBasePrice(model.getBasePrice());
		return product;
	}

	public AttributeModel entity2model(Attribute attribute) {
		AttributeModel model = new AttributeModel();
		model.setId(attribute.getId());
		model.setName(attribute.getName());
		model.setValue(attribute.getValue());
		return model;
	}
	
	public Attribute model2entity(AttributeModel attributeModel) {
		Attribute attribute = new Attribute();
		attribute.setName(attributeModel.getName());
		attribute.setValue(attributeModel.getValue());
		return attribute;
	}	
	

	
	public ProductVariant model2entity (ProductVariantModel productVariantModel, Integer productId) {
		ProductVariant productVariant = new ProductVariant();
		productVariant.setAvailableQuantity(productVariantModel.getAvailableQuantity());
		productVariant.setPriceInfluence(productVariantModel.getPriceInfluence());
		productVariant.setProductId(productId);
		return productVariant;
	}
	
	public List<ProductVariant> listModel2listEntity(List<ProductVariantModel> listProductVariantModel, Integer productId){
		List<ProductVariant> productVariants = new ArrayList<>();
		for (ProductVariantModel productVariantModel : listProductVariantModel) {
			productVariants.add(model2entity(productVariantModel, productId));
		}
		return productVariants;
	}
	
	
	public List<Attribute> listModel2listEntity(List<AttributeModel> listAttributesModel){
		List<Attribute> attributes = new ArrayList<>();
		for (AttributeModel attributeModel : listAttributesModel) {
			attributes.add(model2entity(attributeModel));
		}
		return attributes;
	}
	
}
