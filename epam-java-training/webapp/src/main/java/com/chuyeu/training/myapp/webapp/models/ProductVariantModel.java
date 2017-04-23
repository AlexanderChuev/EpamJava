package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class ProductVariantModel {

	private Integer productId;
	private Integer availableQuantity;
	private Double priceInfluence;
	private List<AttributeModel> attributes;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Double getPriceInfluence() {
		return priceInfluence;
	}

	public void setPriceInfluence(Double priceInfluence) {
		this.priceInfluence = priceInfluence;
	}

	public List<AttributeModel> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeModel> attributes) {
		this.attributes = attributes;
	}

}
