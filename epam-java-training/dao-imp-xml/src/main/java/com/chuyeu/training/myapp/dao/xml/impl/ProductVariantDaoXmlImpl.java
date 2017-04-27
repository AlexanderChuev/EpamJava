package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.Attribute;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVariant get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer add(ProductVariant productVariant) {
		/*File productVariantsFile = new File(rootFolder + "product_variants.xml");
		@SuppressWarnings("unchecked")
		XmlModelWrapper<ProductVariant> wrapper = (XmlModelWrapper<ProductVariant>) xstream.fromXML(productVariantsFile);
		List<ProductVariant> productVariantsFromDb = wrapper.getRows();
		
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;
		
		productVariant.setId(newId);
		productVariantsFromDb.add(productVariant);

		wrapper.setLastId(newId);
		writeNewData(productVariantsFile, wrapper);*/
		
		return null;
	}

	@Override
	public Integer update(ProductVariant productVariant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

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
