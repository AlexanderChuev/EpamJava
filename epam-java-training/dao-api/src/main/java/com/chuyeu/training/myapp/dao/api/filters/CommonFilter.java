package com.chuyeu.training.myapp.dao.api.filters;

import com.chuyeu.training.myapp.datamodel.OrderStatus;

public class CommonFilter {

	private Integer pageNumber;
	private Integer limit;
	private SortData sort;
	private OrderStatus orderStatus;

	public CommonFilter(Integer pageNumber, Integer limit, String column, String direction, OrderStatus orderStatus) {
		super();
		if (pageNumber == null) {
			pageNumber = 1;
		}
		this.limit = limit;
		this.pageNumber = pageNumber;
		sort = new SortData(column, direction);
		this.orderStatus = orderStatus;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

}
