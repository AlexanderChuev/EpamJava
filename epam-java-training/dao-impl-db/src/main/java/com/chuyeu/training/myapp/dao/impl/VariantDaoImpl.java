package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IVariantDao;
import com.chuyeu.training.myapp.dao.util.Converter;

@Repository
public class VariantDaoImpl implements IVariantDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(List<Integer> listId) {
		Converter converter = new Converter();
		String ids = converter.listIdToStringForDelete(listId);
		jdbcTemplate.update("delete from variant where attribute_id IN (" + ids + ")");
	}

	@Override
	public void delete(Integer attributeId) {
		jdbcTemplate.update("delete from variant where attribute_id = " + attributeId);
	}

	@Override
	public void add(Integer productVariantId, Integer attributeId) {

		jdbcTemplate.update("insert into variant (product_variant_id, attribute_id) values (?,?)", productVariantId,
				attributeId);
	}

}
