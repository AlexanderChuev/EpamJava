package com.chuyeu.training.myapp.datamodel;

import java.util.List;

public class ProductVariant extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2816697463793299620L;
	private Product product;
	private Integer quantity;
	private Double priceInfluence;
	private List<Attribute> attributes;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPriceInfluence() {
		return priceInfluence;
	}
	public void setPriceInfluence(Double priceInfluence) {
		this.priceInfluence = priceInfluence;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	
	@Override
	public String toString() {
		return "ProductVariant [product=" + product + ", quantity=" + quantity + ", priceInfluence=" + priceInfluence
				+ ", attributes=" + attributes + "]";
	}
	
}
