package com.chuyeu.training.myapp.dao.xml.impl.comparators;

import java.util.Comparator;

import com.chuyeu.training.myapp.datamodel.Order;

public class OrderDateComparator implements Comparator<Order>{

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getCreated().compareTo(o2.getCreated());
	}

}
