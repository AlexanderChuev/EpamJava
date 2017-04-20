package com.chuyeu.training.myapp.webapp.models;

public class OrderItemModel {

	private ProductVariantModel productVariantModel;
	private Integer orderQuantity;

	public ProductVariantModel getProductVariantModel() {
		return productVariantModel;
	}

	public void setProductVariantModel(ProductVariantModel productVariantModel) {
		this.productVariantModel = productVariantModel;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

}
