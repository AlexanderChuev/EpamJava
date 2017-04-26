package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.Product;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class ProductDaoXmlImpl implements IProductDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	@Override
	public List<Product> getAll(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product get(Integer id) {
		getFile();
		return null;
	}

	@Override
	public Integer add(Product product) {
		File file = getFile();

		XmlModelWrapper<Product> wrapper = (XmlModelWrapper<Product>) xstream.fromXML(file);

		List<Product> products = wrapper.getRows();
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		product.setId(newId);
		products.add(product);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);
		return newId;
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getProductQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

	private void writeNewData(File file, XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile() {
		File file = new File(rootFolder + "books.xml");
		return file;
	}

}
