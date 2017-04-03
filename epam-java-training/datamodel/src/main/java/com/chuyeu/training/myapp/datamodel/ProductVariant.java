package com.chuyeu.training.myapp.datamodel;

public class ProductVariant extends AbstractModel {

	private static final long serialVersionUID = 2816697463793299620L;
	private Integer productId;
	private Integer availableQuantity;
	private Double priceInfluence;

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
		return "ProductVariant [productId=" + productId + ", availableQuantity=" + availableQuantity
				+ ", priceInfluence=" + priceInfluence + "]";
	}

}
