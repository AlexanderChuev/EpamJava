package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.Order;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class OrdersDaoXmlImpl implements IOrdersDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	@Override
	public List<Order> getAll(CommonFilter commonFilter, OrderFilter orderFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Order order) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Order> wrapper = (XmlModelWrapper<Order>) xstream.fromXML(file);
		List<Order> orders = wrapper.getRows();

		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		order.setId(newId);
		Timestamp timestamp = new Timestamp(order.getCreated().getTime());
		System.out.println(timestamp);
		orders.add(order);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);

		return newId;
	}

	@Override
	public void update(Order order) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Order> wrapper = (XmlModelWrapper<Order>) xstream.fromXML(file);
		List<Order> orders = wrapper.getRows();

		for (Order orderFromDb : orders) {
			if (orderFromDb.getId().equals(order.getId())) {
				orderFromDb.setOrderStatus(order.getOrderStatus());
				break;
			}
		}
		writeNewData(file, wrapper);

	}

	@Override
	public void delete(Integer id) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Order> wrapper = (XmlModelWrapper<Order>) xstream.fromXML(file);
		List<Order> orders = wrapper.getRows();
		
		Order found = null;
		for (Order order : orders) {
			if (order.getId().equals(id)) {
				found = order;
				break;
			}
		}
		if (found != null) {
			orders.remove(found);
			writeNewData(file, wrapper);
		}

	}

	@Override
	public Order get(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Order> wrapper = (XmlModelWrapper<Order>) xstream.fromXML(file);
		List<Order> orders = wrapper.getRows();

		for (Order order : orders) {
			if (order.getId().equals(id)) {
				return order;
			}
		}
		throw new EmptyResultDataAccessException("Order was not found, id = ", id);
	}

	private File getFile() {
		File file = new File(rootFolder + "orders.xml");
		return file;
	}

	private void writeNewData(File file, @SuppressWarnings("rawtypes") XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
