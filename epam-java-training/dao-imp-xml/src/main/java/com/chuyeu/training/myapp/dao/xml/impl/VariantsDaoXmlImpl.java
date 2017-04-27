package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IVariantDao;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.Variant;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class VariantsDaoXmlImpl implements IVariantDao {
	
	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;
	
	@Override
	public void delete(Integer attributeId, Integer productVariantId) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Variant> wrapper = (XmlModelWrapper<Variant>) xstream.fromXML(file);
		List<Variant> variantFromDb = wrapper.getRows();

		Variant found = null;
		for (Variant variant : variantFromDb) {
			if(variant.getAttributeId().equals(attributeId) && variant.getProductVariantId().equals(productVariantId)){
				found = variant;
				break;
			}
		}
		
		if (found != null) {
			variantFromDb.remove(found);
			writeNewData(file, wrapper);
		}
	}

	@Override
	public void add(Integer productVariantId, Integer attributeId) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Variant> wrapper = (XmlModelWrapper<Variant>) xstream.fromXML(file);
		List<Variant> variantsFromDb = wrapper.getRows();
		
		Variant variant = new Variant();
		variant.setAttributeId(attributeId);
		variant.setProductVariantId(productVariantId);
		variantsFromDb.add(variant);

		writeNewData(file, wrapper);

	}
	
	private File getFile() {
		File file = new File(rootFolder + "variants.xml");
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
