package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class ProductVariantDaoXmlImpl implements IProductVariantDao {

	private final XStream xstream = new XStream(new DomDriver());
	
	@Value("${root.folder}")
	private String rootFolder;
	
	@Override
	public List<ProductVariant> getAllByProduct(Integer productId) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<ProductVariant> wrapper = (XmlModelWrapper<ProductVariant>) xstream.fromXML(file);
		List<ProductVariant> productVariantsFromDb = wrapper.getRows();
		List<ProductVariant> productVariantsById = new ArrayList<>();
		
		for (ProductVariant productVariant : productVariantsFromDb) {
			if(productVariant.getProductId().equals(productId)){
				System.out.println(productVariant.getId());
				productVariantsById.add(productVariant);
			}
		}
		return productVariantsById;
	}

	@Override
	public ProductVariant get(Integer id) {
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<ProductVariant> wrapper = (XmlModelWrapper<ProductVariant>) xstream.fromXML(file);
		List<ProductVariant> productVariantsFromDb = wrapper.getRows();
		for (ProductVariant productVariant : productVariantsFromDb) {
			if(productVariant.getId().equals(id)){
				return productVariant;
			}
		}
		throw new EmptyResultDataAccessException("This product variant is already exist",0);
	}

	@Override
	public Integer add(ProductVariant productVariant) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<ProductVariant> wrapper = (XmlModelWrapper<ProductVariant>) xstream.fromXML(file);
		List<ProductVariant> productVariantsFromDb = wrapper.getRows();
		
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;
		
		productVariant.setId(newId);
		productVariantsFromDb.add(productVariant);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);
		
		return newId;
	}

	@Override
	public Integer update(ProductVariant productVariant) {
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<ProductVariant> wrapper = (XmlModelWrapper<ProductVariant>) xstream.fromXML(file);
		List<ProductVariant> productVariantsFromDb = wrapper.getRows();
		for (ProductVariant productVariantFromDb : productVariantsFromDb) {
			if(productVariantFromDb.getId().equals(productVariant.getId())){
				productVariantFromDb.setPriceInfluence(productVariant.getPriceInfluence());
				productVariantFromDb.setAvailableQuantity(productVariant.getAvailableQuantity());
				break;
			}
		}
		writeNewData(file, wrapper);
		return productVariant.getId(); ////// change
	}

	@Override
	public void delete(Integer id) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<ProductVariant> wrapper = (XmlModelWrapper<ProductVariant>) xstream.fromXML(file);
		List<ProductVariant> productVariantsFromDb = wrapper.getRows();

		ProductVariant found = null;
		for (ProductVariant productVariantFromDb : productVariantsFromDb) {
			if (productVariantFromDb.getId().equals(id)) {
				found = productVariantFromDb;
				break;
			}
		}
		if (found != null) {
			productVariantsFromDb.remove(found);
			writeNewData(file, wrapper);
		}
	}
	
	private File getFile() {
		File file = new File(rootFolder + "product_variants.xml");
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
