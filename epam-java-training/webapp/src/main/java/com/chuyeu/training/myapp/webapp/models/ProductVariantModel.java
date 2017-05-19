package com.chuyeu.training.myapp.webapp.models;

import java.io.Serializable;

public class ProductVariantModel implements Serializable {

	private Integer id;
	private Integer productId;
	private Integer availableQuantity;
	private Double priceInfluence;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "ProductVariantModel [id=" + id + ", productId=" + productId + ", availableQuantity=" + availableQuantity
				+ ", priceInfluence=" + priceInfluence + "]";
	}

}
