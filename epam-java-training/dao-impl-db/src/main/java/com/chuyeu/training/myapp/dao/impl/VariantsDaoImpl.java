package com.chuyeu.training.myapp.dao.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IVariantsDao;

@Repository
public class VariantsDaoImpl implements IVariantsDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(String ids) {
		jdbcTemplate.update("delete from variants where attribute_id IN ("+ids+")");
		
	}
	
	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from variants where attribute_id = "+id);
		
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

}
