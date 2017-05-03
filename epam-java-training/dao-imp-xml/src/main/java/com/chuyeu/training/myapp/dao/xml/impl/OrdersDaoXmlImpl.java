package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.dao.xml.impl.comparators.OrderDateComparator;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class OrdersDaoXmlImpl implements IOrdersDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	@Override
	public List<Order> getAll(CommonFilter commonFilter, OrderFilter orderFilter) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Order> wrapper = (XmlModelWrapper<Order>) xstream.fromXML(file);
		List<Order> orders = wrapper.getRows();

		List<Order> found = new ArrayList<>();

		for (Order order : orders) {
			if (orderFilter.getUserRole().equals(UserRole.ADMIN)) {
				if (order.getOrderStatus().equals(orderFilter.getOrderStatus())) {
					found.add(order);
				}
			} else {
				if (order.getUserProfileId().equals(orderFilter.getId())
						&& order.getOrderStatus().equals(orderFilter.getOrderStatus())) {
					found.add(order);
				}
			}
		}

		
		if (commonFilter.getSort().getColumn().toUpperCase().equals("CREATED")) {
			Collections.sort(found, new OrderDateComparator());
		}

		if (commonFilter.getSort().getDirection().toUpperCase().equals("DESC")) {
			Collections.reverse(found);
		}

		List<Order> orderFiltered = new ArrayList<>();
		int from, to;

		if (commonFilter.getPageNumber() == null || commonFilter.getPageNumber().equals(0)
				|| commonFilter.getPageNumber().equals(1)) {
			from = 0;
			to = commonFilter.getLimit();
		} else {
			from = commonFilter.getLimit() * (commonFilter.getPageNumber() - 1);
			to = from + commonFilter.getLimit();
		}

		if (found.size() < to) {
			to = found.size();
		}

		for (; from < to; from++) {
			orderFiltered.add(found.get(from));
		}
		return orderFiltered;
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
