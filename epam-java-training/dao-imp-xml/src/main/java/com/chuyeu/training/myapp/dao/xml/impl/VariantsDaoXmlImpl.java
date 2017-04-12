package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IVariantsDao;

@Repository
public class VariantsDaoXmlImpl implements IVariantsDao{
	

	@Override
	public void delete(List<Integer> listId) {
	}
	
	@Override
	public void delete(Integer id) {
		
	}

	@Override
	public void add(Integer productVariantId, List<Integer> listAttributeId) {
	}

}
