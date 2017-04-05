package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;

@Repository
public class AttributeDaoImpl implements IAttributeDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Attribute insert(Attribute attribute) throws DuplicateKeyException {
		final String INSERT_SQL = "insert into attribute (name, value) values(?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, attribute.getName());
				ps.setString(2, attribute.getValue());
				return ps;
			}
		}, keyHolder);

		attribute.setId(keyHolder.getKey().intValue());

		return attribute;
	}

	@Override
	public void deleteValue(Integer id) {
		jdbcTemplate.update("delete from attribute where id = " + id);
	}
	
	@Override
	public void deleteName(String name) {
		jdbcTemplate.update("delete from attribute where name = " + name);
	}

	@Override
	public List<Attribute> getProductVariantAttributes(Integer productVariantId) {
		return jdbcTemplate.query(
				"select * from attribute a join variants v on v.attribute_id = a.id where v.product_variant_id = ?",
				new Object[] { productVariantId }, new BeanPropertyRowMapper<Attribute>(Attribute.class));
	}

	@Override
	public List<String> getNames() {
		return jdbcTemplate.queryForList("select distinct name from attribute", String.class);
	}

	@Override
	public List<String> getValuesByName(String name) {
		return jdbcTemplate.queryForList("select value from attribute where name = ?", new Object[] { name }, String.class);
	}

	@Override
	public Integer getIdByNameAndValue(String name, String value) {
		return jdbcTemplate.queryForObject("select id from attribute where name = ? and value = ?", new Object[] { name, value }, Integer.class);
	}

}
