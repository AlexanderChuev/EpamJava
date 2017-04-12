package com.chuyeu.training.myapp.dao.api.filters;

public class ProductFilter {

	private Integer limit;
	private Integer pageNumber;
	private SortData sort;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public SortData getSort() {
		return sort;
	}

	public void setSort(SortData sort) {
		this.sort = sort;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

}
