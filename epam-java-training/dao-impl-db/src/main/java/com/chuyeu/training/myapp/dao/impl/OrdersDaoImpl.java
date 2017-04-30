package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.dao.mapper.OrderMapper;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.UserRole;

@Repository
public class OrdersDaoImpl implements IOrdersDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Order> getAll(CommonFilter commonFilter, OrderFilter orderFilter) {
		String sql = createSql(commonFilter, orderFilter);
		return jdbcTemplate.query(sql, new OrderMapper());
	}

	@Override
	public Order get(Integer id) {
		return jdbcTemplate.queryForObject("select * from orders where id = ? ", new Object[] { id },
				new OrderMapper());
	}

	@Override
	public Integer save(Order order) {

		final String INSERT_SQL = "insert into orders (created, user_profile_id, total_price, order_status) values(?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setTimestamp(1, new Timestamp(order.getCreated().getTime()));
				ps.setInt(2, order.getUserProfileId());
				ps.setDouble(3, order.getTotalPrice());
				ps.setString(4, order.getOrderStatus().toString());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}

	@Override
	public void update(Order order) {
		jdbcTemplate.update("update orders set order_status = ? " + "where id = ?", order.getOrderStatus().toString(),
				order.getId());

	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from orders where id=" + id);
	}

	private String createSql(CommonFilter commonFilter, OrderFilter orderFilter) {

		StringBuilder sql = new StringBuilder("");
		if (orderFilter.getUserRole().equals(UserRole.CLIENT)) {

			sql.append(
					"select orders.id, created, user_profile_id, total_price, order_status from orders, user_profile where orders.user_profile_id = user_profile.id and user_profile.user_credentials_id = ");
			sql.append(orderFilter.getId());
			sql.append(" and order_status = '");
			sql.append(orderFilter.getOrderStatus());
			sql.append("'");

		} else {
			sql.append("select * from orders where order_status = '");
			sql.append(orderFilter.getOrderStatus());
			sql.append("'");

		}

		if (commonFilter.getSort() != null && commonFilter.getSort().getColumn() != null) {
			sql.append(" order by ");
			sql.append(commonFilter.getSort().getColumn());
			sql.append(" ");
			if (commonFilter.getSort().getDirection() != null) {
				sql.append(commonFilter.getSort().getDirection());
			}
		}
		return sql.toString();
	}

}
