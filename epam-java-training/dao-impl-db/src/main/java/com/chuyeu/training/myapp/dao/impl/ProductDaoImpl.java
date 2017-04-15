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

import com.chuyeu.training.myapp.dao.api.IProductDao;
import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;

@Repository
public class ProductDaoImpl implements IProductDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Product get(Integer id) {
		return jdbcTemplate.queryForObject("select * from product where id = ?", new Object []{ id }, new BeanPropertyRowMapper<Product>(Product.class));		
	}
	
	@Override
	public List<Product> getAll(ProductFilter productFilter) {
		String sql = createSql(productFilter);
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	@Override
	public Integer getProductQuantity() {
		return jdbcTemplate.queryForObject("select count (*) from product", Integer.class);
	}

	@Override
	public Integer add(Product product) {
		
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

        

        return keyHolder.getKey().intValue();
	}

	@Override
	public void update(Product product) {
		jdbcTemplate.update("update product set name = ?, description = ?, starting_price = ?, active = ?"
				+ " where id = ?" , product.getName(), product.getDescription(), product.getStartingPrice(), product.getActive() ,product.getId());
	}

	@Override
	public void delete(Integer id) throws EmptyResultDataAccessException{
		jdbcTemplate.update("delete from product where id=" + id);		
	}
	

	public String createSql(ProductFilter productFilter){
		
		Integer offset = productFilter.getLimit()*(productFilter.getPageNumber() - 1);
		
		StringBuilder stringBuilder = new StringBuilder("select * from product ");
		
		if (productFilter.getSort().getColumn() != null){
			stringBuilder.append("order by ").append(productFilter.getSort().getColumn());
			
			if ("desc".equals(productFilter.getSort().getDirection())){
				stringBuilder.append(" desc");
			}
		}
		
		stringBuilder.append(" limit ");
		stringBuilder.append(productFilter.getLimit());
		stringBuilder.append(" offset ");
		stringBuilder.append(offset);
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}

}
