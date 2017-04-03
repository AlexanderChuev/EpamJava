package com.chuyeu.training.myapp.services.entity;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

public class ProductVariantEntity {

	private ProductVariant productVariant;
	private Product product;
	private List<Attribute> attributes;

	public ProductVariantEntity() {
	}

	public ProductVariantEntity(ProductVariant productVariant, Product product, List<Attribute> attributes) {
		this.productVariant = productVariant;
		this.product = product;
		this.attributes = attributes;
	}

	public ProductVariant getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "ProductVariantEntity [productVariant=" + productVariant.toString() + ", product=" + product.toString() + ", attributes="
				+ attributes.toString() + "]";
	}

	
}
