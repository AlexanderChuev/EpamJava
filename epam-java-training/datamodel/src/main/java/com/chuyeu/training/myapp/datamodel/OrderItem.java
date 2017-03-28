package com.chuyeu.training.myapp.datamodel;

public class OrderItem extends AbstractModel {

	private static final long serialVersionUID = -6334637123535313625L;

	private ProductVariant productVariant;
	private Integer orderQuantity;
	private Order order;

	public ProductVariant getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItem [productVariant=" + productVariant + ", orderQuantity=" + orderQuantity + ", order=" + order
				+ "]";
	}

}
