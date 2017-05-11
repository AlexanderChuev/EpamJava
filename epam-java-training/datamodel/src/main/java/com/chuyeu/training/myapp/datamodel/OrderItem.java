package com.chuyeu.training.myapp.datamodel;

public class OrderItem extends AbstractModel {

	private Integer productVariantId;
	private Integer orderQuantity;
	private Integer orderId;
	private Double price;

	public Integer getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Integer productVariantId) {
		this.productVariantId = productVariantId;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem [productVariantId=" + productVariantId + ", orderQuantity=" + orderQuantity + ", orderId="
				+ orderId + ", price=" + price + "]";
	}

}
