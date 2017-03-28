package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IProductVariantDao;
import com.chuyeu.training.myapp.dao.mapper.ProductVariantMapper;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

@Repository
public class ProductVariantDaoImpl implements IProductVariantDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	// +++
	@Override
	public List<ProductVariant> getAll() {
		return jdbcTemplate.query( "select * from product_variant pv, product p, variants v, attribute a where pv.product_id = p.id "
				+ "and pv.id = v.product_variant_id and a.id = v.attribute_id", new ProductVariantMapper());
		
	}
	
	// +++
	@Override
	public ProductVariant get(Integer id) {
		
		/*ProductVariant productVariant = jdbcTemplate.queryForObject("select * from product_variant pv "
				+ "inner join product p on pv.product_id = p.id "
				+ "where pv.id = ?", new Object[] { id }, new ProductVariantMapper());
		
		productVariant.setAttributes(getAttributes(id));*/
		
		return jdbcTemplate.queryForObject( "select * from product_variant pv, product p, variants v, attribute a where pv.product_id = p.id "
				+ "and pv.id = v.product_variant_id and a.id = v.attribute_id and pv.id = ?", new Object[] { id }, new ProductVariantMapper());
		
	//	return productVariant;
		
		
	}

	/*private List<Attribute> getAttributes(Integer id){
		
		return jdbcTemplate.query("select * from variants v "
				+ "inner join attribute a on a.id = v.attribute_id "
				+ "inner join product_variant pv on v.product_variant_id = pv.id "
				+ "where pv.id = ?",  new Object[] { id }, new AttributesMapper());
	}*/
	
	@Override
	public ProductVariant insert(ProductVariant productVariant) {

		final String INSERT_SQL = "insert into product_variant (product_id, quantity, price_influence) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, productVariant.getProduct().getId());
				ps.setInt(2, productVariant.getQuantity());
				ps.setDouble(3, productVariant.getPriceInfluence());
				return ps;
			}
		}, keyHolder);

		productVariant.setId(keyHolder.getKey().intValue());

		return productVariant;
	}

	@Override
	public ProductVariant update(ProductVariant product_variant) {
		jdbcTemplate.update(
				"update product_variant set product_id = ?, quantity = ?, price_influence = ?" + " where id = ?",
				product_variant.getProduct().getId(), product_variant.getQuantity(), product_variant.getPriceInfluence(),
				product_variant.getId());
		return get(product_variant.getId());  // ничего не возвращает
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from product_variant where id=" + id);	

	}

}
