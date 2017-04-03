package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IProductDao;
import com.chuyeu.training.myapp.datamodel.Product;

@Repository
public class ProductDaoImpl implements IProductDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Product get(Integer id) {
		return jdbcTemplate.queryForObject("select * from product where id = ? ", new Object []{ id }, new BeanPropertyRowMapper<Product>(Product.class));		
	}
	
	@Override
	public List<Product> getAll() {
		return jdbcTemplate.query("select * from product", new BeanPropertyRowMapper<Product>(Product.class));
	}


	@Override
	public Product insert(Product product) {
		
		final String INSERT_SQL = "insert into product (name, description, starting_price, active) values(?, ?, ?, ?)";
		
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setDouble(3, product.getStartingPrice());
                ps.setBoolean(4, product.getActive());
                return ps;
            }
        }, keyHolder);

        product.setId(keyHolder.getKey().intValue());

        return product;
	}

	@Override
	public Product update(Product product) {
		jdbcTemplate.update("update product set name = ?, description = ?, starting_price = ?, active = ?"
				+ " where id = ?" , product.getName(), product.getDescription(), product.getStartingPrice(), product.getActive() ,product.getId());
		return get(product.getId());
	}

	@Override
	public void delete(Integer id) throws EmptyResultDataAccessException{
		jdbcTemplate.update("delete from product where id=" + id);		
	}



	
}
