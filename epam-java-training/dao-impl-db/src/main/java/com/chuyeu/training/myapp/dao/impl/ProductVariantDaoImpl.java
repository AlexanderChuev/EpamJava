package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IProductVariantDao;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

@Repository
public class ProductVariantDaoImpl implements IProductVariantDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	// +++ возможно не нужен вообще!
	@Override
	public List<ProductVariant> getAll() {
		return jdbcTemplate.query("select * from product_variant",
				new BeanPropertyRowMapper<ProductVariant>(ProductVariant.class));

	}

	// +++
	@Override
	public ProductVariant get(Integer id) {

		return jdbcTemplate.queryForObject("select * from product_variant where id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<ProductVariant>(ProductVariant.class));

	}

	@Override
	public void add(ProductVariant productVariant) {
		jdbcTemplate.update("insert into product_variant (product_id, available_quantity, price_influence) values(?, ?, ?)",
				productVariant.getProductId(), productVariant.getAvailableQuantity(),
				productVariant.getPriceInfluence());
	}

	@Override
	public ProductVariant update(ProductVariant product_variant) {
		jdbcTemplate.update(
				"update product_variant set product_id = ?, available_quantity = ?, price_influence = ?" + " where id = ?",
				product_variant.getProductId(), product_variant.getAvailableQuantity(),
				product_variant.getPriceInfluence(), product_variant.getId());
		return get(product_variant.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from product_variant where id=" + id);

	}

	@Override
	public List<ProductVariant> getAllByProduct(Integer productId) {
		return jdbcTemplate.query("select * from product_variant where product_id = ?", new Object[] { productId },
				new BeanPropertyRowMapper<ProductVariant>(ProductVariant.class));
	}

}
