package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Variant;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class AttributeDaoXmlImpl implements IAttributeDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	@Override
	public List<Attribute> getProductVariantAttributes(Integer productVariantId) {

		File file = getFile();
		File variantsFile = getVariantsFile();

		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> attributeWrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);

		@SuppressWarnings("unchecked")
		XmlModelWrapper<Variant> variantWrapper = (XmlModelWrapper<Variant>) xstream.fromXML(variantsFile);

		List<Attribute> attributesFromDb = attributeWrapper.getRows();
		List<Variant> variantsFromDb = variantWrapper.getRows();
		List<Attribute> productVariantAttributes = new ArrayList<>();

		for (Variant variant : variantsFromDb) {
			if (variant.getProductVariantId().equals(productVariantId)) {
				Integer attributeId = variant.getAttributeId();
				for (Attribute attribute : attributesFromDb) {
					if (attribute.getId().equals(attributeId)) {
						productVariantAttributes.add(attribute);
					}
				}
			}
		}
		return productVariantAttributes;
	}

	@Override
	public List<String> getNames() {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);

		List<Attribute> attributesFromDb = wrapper.getRows();
		List<String> names = new ArrayList<>();

		for (Attribute attribute : attributesFromDb) {
			names.add(attribute.getName());
		}
		return names;
	}

	@Override
	public List<String> getValuesByName(String attributeName) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);

		List<Attribute> attributesFromDb = wrapper.getRows();
		List<String> values = new ArrayList<>();

		for (Attribute attribute : attributesFromDb) {
			if (attribute.getName().equals(attributeName)) {
				values.add(attribute.getValue());
			}
		}
		return values;
	}

	@Override
	public Integer getIdByNameAndValue(String attributeName, String attributeValue) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);
		List<Attribute> attributesFromDb = wrapper.getRows();

		for (Attribute attribute : attributesFromDb) {
			if (attribute.getName().equals(attributeName) && attribute.getValue().equals(attributeValue)) {
				return attribute.getId();
			}
		}
		throw new EmptyResultDataAccessException("Atribute was not found", 0);
	}

	@Override
	public void deleteAttributeValue(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);
		List<Attribute> attributesFromDb = wrapper.getRows();

		Attribute found = null;
		for (Attribute attribute : attributesFromDb) {
			if (attribute.getId().equals(id)) {
				found = attribute;
				break;
			}
		}

		if (found != null) {
			attributesFromDb.remove(found);
			writeNewData(file, wrapper);
		}
	}

	@Override
	public void deleteByName(String name) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);
		List<Attribute> attributesFromDb = wrapper.getRows();

		Iterator<Attribute> it = attributesFromDb.iterator();
		while (it.hasNext()) {
			Attribute attribute = it.next();
			if (attribute.getName().equals(name)) {
				it.remove();
			}
		}

		writeNewData(file, wrapper);
	}

	@Override
	public void save(Attribute attribute) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);
		List<Attribute> attributesFromDb = wrapper.getRows();

		for (Attribute attributeFromDb : attributesFromDb) {
			if (attributeFromDb.getName().equals(attribute.getName())
					&& attributeFromDb.getValue().equals(attribute.getValue())) {
				throw new DuplicateKeyException("This attribute is already exist");
			}
		}

		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		attribute.setId(newId);
		attributesFromDb.add(attribute);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);

	}

	@Override
	public List<Integer> getAllIdByName(String name) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<Attribute> wrapper = (XmlModelWrapper<Attribute>) xstream.fromXML(file);

		List<Attribute> attributesFromDb = wrapper.getRows();
		List<Integer> ids = new ArrayList<>();

		for (Attribute attribute : attributesFromDb) {
			if (attribute.getName().equals(name)) {
				ids.add(attribute.getId());
			}
		}
		return ids;
	}

	private void writeNewData(File file, @SuppressWarnings("rawtypes") XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile() {
		File file = new File(rootFolder + "attributes.xml");
		return file;
	}

	private File getVariantsFile() {
		File file = new File(rootFolder + "variants.xml");
		return file;
	}
}
