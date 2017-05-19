package com.chuyeu.training.myapp.dao.api.filters;

public class CommonFilter {

	private Integer pageNumber;
	private Integer limit;
	private SortData sort;

	public CommonFilter(Integer pageNumber, Integer limit, String column, String direction) {
		super();
		if (pageNumber == null) {
			pageNumber = 1;
		}
		this.limit = limit;
		this.pageNumber = pageNumber;
		sort = new SortData(column, direction);
	}

	public Integer getPageNumber() {
		if (pageNumber == null || pageNumber.equals(new Integer(0))) {
			pageNumber = 1;
		}
		return pageNumber;
	}

	public Integer getLimit() {
		if (limit == null) {
			limit = 2;
		}
		return limit;
	}

	public SortData getSort() {
		return sort;
	}

	@Override
	public String toString() {
		return "CommonFilter [pageNumber=" + pageNumber + ", limit=" + limit + ", sort=" + sort + "]";
	}

}
