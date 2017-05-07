package com.chuyeu.training.myapp.webapp.models;

import java.io.Serializable;

public class VariantModel implements Serializable {

	private Integer productVariantId;
	private Integer attributeId;

	public Integer getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Integer productVariantId) {
		this.productVariantId = productVariantId;
	}

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	@Override
	public String toString() {
		return "VariantModel [productVariantId=" + productVariantId + ", attributeId=" + attributeId + "]";
	}

}
