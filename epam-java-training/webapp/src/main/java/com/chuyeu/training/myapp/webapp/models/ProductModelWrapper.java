package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class ProductModelWrapper {

	private List<ProductModel> listProductModel;
	private Integer pageCount;

	public List<ProductModel> getListProductModel() {
		return listProductModel;
	}

	public void setListProductModel(List<ProductModel> listProductModel) {
		this.listProductModel = listProductModel;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
