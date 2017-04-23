package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class VariantsModel {

	private Integer productVariantId;
	private List<Integer> attributeIds;

	public Integer getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Integer productVariantId) {
		this.productVariantId = productVariantId;
	}

	public List<Integer> getAttributeIds() {
		return attributeIds;
	}

	public void setAttributeIds(List<Integer> attributeIds) {
		this.attributeIds = attributeIds;
	}

}
