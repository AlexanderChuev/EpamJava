package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;

@Repository
public class OrderItemDaoXmlImpl implements IOrderItemDao {

	
	@Override
	public List<OrderItem> getAll() {
		return null;
	}

	@Override
	public OrderItem get(Integer id) {
		return null;		
	}

	@Override
	public OrderItem insert(OrderItem orderItem) {
		return null;
	}

	@Override
	public OrderItem update(OrderItem orderItem) {
		return null;
	}

	@Override
	public void delete(Integer id){
		
	}

}
