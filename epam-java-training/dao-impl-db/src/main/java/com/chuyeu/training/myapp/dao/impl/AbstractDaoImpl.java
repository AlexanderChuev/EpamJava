package com.chuyeu.training.myapp.dao.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.chuyeu.training.myapp.dao.api.AbstractDao;

public class AbstractDaoImpl<T> implements AbstractDao<T> {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private final Class<T> type;
	
	protected AbstractDaoImpl(final Class<T> type) {
		this.type = type;
    }

	@Override
	public T get(Integer id) {
		return jdbcTemplate.queryForObject("select * from \"" + getTableName(type) + "\" where id = ?", new Object[] { id }, new BeanPropertyRowMapper<T>(type));
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from \"" + getTableName(type) + "\" where id = " + id);

	}
	
	private String getTableName(Class<T> type) {
        return type.getSimpleName().replaceAll("(.)([A-Z])", "$1_$2").toLowerCase();
    }

}
