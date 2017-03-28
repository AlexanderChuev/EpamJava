package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public List<Attribute> getAll() {
		return jdbcTemplate.query("select * from attribute", new BeanPropertyRowMapper<Attribute>(Attribute.class));
	}

	@Override
	public Attribute get(Integer id) {
		return jdbcTemplate.queryForObject("select * from attribute where id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<Attribute>(Attribute.class));
	}

	@Override
	public Attribute insert(Attribute attribute) throws DuplicateKeyException{
		final String INSERT_SQL = "insert into attribute (attribute_name, value) values(?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, attribute.getAttributeName());
				ps.setString(2, attribute.getValue());
				return ps;
			}
		}, keyHolder);

		attribute.setId(keyHolder.getKey().intValue());

		return attribute;
	}

	@Override
	public Attribute update(Attribute attribute) throws DuplicateKeyException{
		jdbcTemplate.update("update attribute set attribute_name = ?, value = ? " + "where id = ?",
				attribute.getAttributeName(), attribute.getValue(), attribute.getId());
		return get(attribute.getId()); 
	}

	@Override
	public void delete(Integer id) throws EmptyResultDataAccessException {

		jdbcTemplate.update("delete from attribute where id=" + id);
	}

}
