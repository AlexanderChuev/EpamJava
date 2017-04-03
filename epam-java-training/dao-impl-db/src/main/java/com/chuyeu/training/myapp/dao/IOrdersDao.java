package com.chuyeu.training.myapp.dao;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

public interface IOrdersDao extends IAbstractDao<Order> {

	Order getOrderByStatus(Integer id, OrderStatus orderStatus);

}
