package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Order;

@Repository
public class OrdersDaoXmlImpl implements IOrdersDao {

	@Override
	public List<Order> getAll(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
