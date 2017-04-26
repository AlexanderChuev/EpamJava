package com.chuyeu.training.myapp.dao.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IVariantDao;

@Repository
public class VariantDaoImpl implements IVariantDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(Integer attributeId, Integer productVariantId) {
		jdbcTemplate.update("delete from variant where attribute_id = ? and product_variant_id = ?", attributeId, productVariantId);
	}

	@Override
	public void add(Integer productVariantId, Integer attributeId) {

		jdbcTemplate.update("insert into variant (product_variant_id, attribute_id) values (?,?)", productVariantId,
				attributeId);
	}

}
