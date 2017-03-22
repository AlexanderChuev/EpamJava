package com.chuyeu.training.myapp.datamodel;

public class OrderItem extends AbstractModel{
	
	private ProductVariant productVariant;
	private Integer quantity;
	private Integer orderId;
	
	
	public ProductVariant getProductVariant() {
		return productVariant;
	}
	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
