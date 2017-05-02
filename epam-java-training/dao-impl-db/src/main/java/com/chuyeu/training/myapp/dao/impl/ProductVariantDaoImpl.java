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

import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

@Repository
public class ProductVariantDaoImpl implements IProductVariantDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	// +++
	@Override
	public ProductVariant get(Integer id) {

		return jdbcTemplate.queryForObject("select * from product_variant where id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<ProductVariant>(ProductVariant.class));

	}

	@Override
	public Integer add(ProductVariant productVariant) {
		
		final String INSERT_SQL = "insert into product_variant (product_id, available_quantity, price_influence) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, productVariant.getProductId());
				ps.setInt(2, productVariant.getAvailableQuantity());
				ps.setDouble(3, productVariant.getPriceInfluence());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}


	@Override
	public void update(ProductVariant productVariant) { 
		jdbcTemplate.update("update product_variant set available_quantity = ?, price_influence = ?" + " where id = ?",
				productVariant.getAvailableQuantity(), productVariant.getPriceInfluence(), productVariant.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from product_variant where id=" + id);

	}

	@Override
	public List<ProductVariant> getAllByProduct(Integer productId) throws EmptyResultDataAccessException {
		return jdbcTemplate.query("select * from product_variant where product_id = ?", new Object[] { productId },
				new BeanPropertyRowMapper<ProductVariant>(ProductVariant.class));
	}

}
