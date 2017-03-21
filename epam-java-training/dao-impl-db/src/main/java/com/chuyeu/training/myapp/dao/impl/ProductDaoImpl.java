package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IProductDao;
import com.chuyeu.training.myapp.datamodel.Product;

@Repository
public class ProductDaoImpl implements IProductDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Product get(Integer id) {
		return jdbcTemplate.queryForObject("select * from book where id = ? ", new Object []{ id }, new BeanPropertyRowMapper<Product>(Product.class));		
	}
	
	@Override
	public List<Product> getAll() {
		return null;
	}


	@Override
	public Product insert(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}



	
}
