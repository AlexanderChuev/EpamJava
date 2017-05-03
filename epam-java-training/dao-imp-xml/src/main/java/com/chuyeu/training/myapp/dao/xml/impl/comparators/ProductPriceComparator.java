package com.chuyeu.training.myapp.dao.xml.impl.comparators;

import java.util.Comparator;

import com.chuyeu.training.myapp.datamodel.Product;

public class ProductPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		return o1.getBasePrice().compareTo(o2.getBasePrice());
	}

}
