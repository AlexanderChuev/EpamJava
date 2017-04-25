package com.chuyeu.training.myapp.dao.api.filters;

public class ProductFilter {

	private Integer pageNumber;
	private Integer limit;
	private SortData sort;

	public ProductFilter(Integer pageNumber, Integer limit, String column, String direction) {
		super();
		if (pageNumber == null) {
			pageNumber=1;
		}
		this.limit = limit;
		this.pageNumber = pageNumber;
		sort = new SortData(column, direction);
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getLimit() {
		return limit;
	}

	public SortData getSort() {
		return sort;
	}

}
