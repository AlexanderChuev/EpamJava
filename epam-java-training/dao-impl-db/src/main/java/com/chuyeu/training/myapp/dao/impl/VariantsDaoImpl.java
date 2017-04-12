package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IVariantsDao;
import com.chuyeu.training.myapp.dao.util.Converter;

@Repository
public class VariantsDaoImpl implements IVariantsDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(List<Integer> listId) {
		Converter converter = new Converter();
		String ids = converter.listIdToStringForDelete(listId);
		jdbcTemplate.update("delete from variants where attribute_id IN ("+ids+")");
		
	}
	
	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from variants where attribute_id = "+id);
		
	}

	@Override
	public void add(Integer productVariantId, List<Integer> listAttributeId) {
		Converter converter = new Converter();
		String req = converter.listIdToStringForAdd(productVariantId, listAttributeId);
		jdbcTemplate.update("insert into variants (product_variant_id, attribute_id) values "+ req);
		
	}

}
