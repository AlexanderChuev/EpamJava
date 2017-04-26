package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class AttributeDaoXmlImpl implements IAttributeDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Override
	public List<Attribute> getProductVariantAttributes(Integer productVariantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getValuesByName(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getIdByNameAndValue(String attributeName, String attributeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAttributeValue(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Integer> getAllIdByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
