package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class ProductVariantModel {

	private Integer availableQuantity;
	private Double priceInfuence;
	private List<AttributeModel> attributes;
	

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Double getPriceInfuence() {
		return priceInfuence;
	}

	public void setPriceInfuence(Double priceInfuence) {
		this.priceInfuence = priceInfuence;
	}

	public List<AttributeModel> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeModel> attributes) {
		this.attributes = attributes;
	}

}
