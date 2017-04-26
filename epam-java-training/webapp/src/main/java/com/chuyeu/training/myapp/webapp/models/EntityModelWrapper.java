package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class EntityModelWrapper<T> {

	private List<T> listEntityModel;
	private Integer pageCount;

	public List<T> getListEntityModel() {
		return listEntityModel;
	}

	public void setListEntityModel(List<T> listEntityModel) {
		this.listEntityModel = listEntityModel;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
