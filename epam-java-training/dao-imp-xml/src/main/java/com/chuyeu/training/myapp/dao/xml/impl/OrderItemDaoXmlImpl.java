package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrderItemDao;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class OrderItemDaoXmlImpl implements IOrderItemDao {
	
	private final XStream xstream = new XStream(new DomDriver());
	
	@Value("${root.folder}")
	private String rootFolder;
	
	
	@Override
	public List<OrderItem> getAll(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(OrderItem orderItem) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<OrderItem> wrapper = (XmlModelWrapper<OrderItem>) xstream.fromXML(file);
		List<OrderItem> orderItems = wrapper.getRows();
		
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		orderItem.setId(newId);
		orderItems.add(orderItem);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);

	}

	@Override
	public void update(OrderItem orderItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<OrderItem> wrapper = (XmlModelWrapper<OrderItem>) xstream.fromXML(file);
		List<OrderItem> orderItems = wrapper.getRows();
		
		OrderItem found = null;
		for (OrderItem orderItem : orderItems) {
			if (orderItem.getId().equals(id)) {
				found = orderItem;
				break;
			}
		}
		if (found != null) {
			orderItems.remove(found);
			writeNewData(file, wrapper);
		}

	}
	
	private File getFile() {
		File file = new File(rootFolder + "order_items.xml");
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
