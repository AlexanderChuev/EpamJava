package com.chuyeu.training.myapp.datamodel;

public class OrderItem extends AbstractModel {

	private static final long serialVersionUID = -6334637123535313625L;

	private Integer productVariantId;
	private Integer orderQuantity;
	private Integer ordersId;

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

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	@Override
	public String toString() {
		return "OrderItem [productVariantId=" + productVariantId + ", orderQuantity=" + orderQuantity + ", ordersId="
				+ ordersId + "]";
	}

}
