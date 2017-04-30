package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
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
		throw new UnsupportedOperationException();
	}

	@Override
	public Product get(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Product> wrapper = (XmlModelWrapper<Product>) xstream.fromXML(file);
		List<Product> products = wrapper.getRows();

		for (Product product : products) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		throw new EmptyResultDataAccessException("Product was not found, id = ",id);
	}

	@Override
	public Integer add(Product product) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Product> wrapper = (XmlModelWrapper<Product>) xstream.fromXML(file);
		List<Product> productsFromDb = wrapper.getRows();
		
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		product.setId(newId);
		productsFromDb.add(product);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);
		return newId;
	}

	@Override
	public void update(Product product) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Product> wrapper = (XmlModelWrapper<Product>) xstream.fromXML(file);
		List<Product> productsFromDb = wrapper.getRows();
		
		for (Product productFromDb : productsFromDb) {
			if (productFromDb.getId().equals(product.getId())) {
				productFromDb.setName(product.getName());
				productFromDb.setDescription(product.getDescription());
				productFromDb.setBasePrice(product.getBasePrice());
				productFromDb.setActive(product.getActive());
				break;
			}
		}
		writeNewData(file, wrapper);
	}

	@Override
	public void delete(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Product> wrapper = (XmlModelWrapper<Product>) xstream.fromXML(file);
		List<Product> productsFromDb = wrapper.getRows();
		
		Product found = null;
		for (Product productFromDb : productsFromDb) {
			if (productFromDb.getId().equals(id)) {
				found = productFromDb;
				break;
			}
		}
		if (found != null) {
			productsFromDb.remove(found);
			writeNewData(file, wrapper);
		}

	}

	@Override
	public Integer getProductQuantity() {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Product> wrapper = (XmlModelWrapper<Product>) xstream.fromXML(file);
		List<Product> productsFromDb = wrapper.getRows();

		return productsFromDb.size();
	}

	private void writeNewData(File file, @SuppressWarnings("rawtypes") XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile() {
		File file = new File(rootFolder + "products.xml");
		return file;
	}

}
