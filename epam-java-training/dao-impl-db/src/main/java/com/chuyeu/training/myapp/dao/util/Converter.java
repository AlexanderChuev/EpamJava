package com.chuyeu.training.myapp.dao.util;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;

public class Converter {

	public String listIdToStringForDelete(List<Integer> listId) {
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("(");
		for (Integer id : listId) {
			stringBuilder.append(id);
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		return stringBuilder.toString();

	}

	public String listIdToStringForAdd(Integer productVariantId, List<Integer> listId) {
		StringBuilder stringBuilder = new StringBuilder("");
		for (Integer id : listId) {
			stringBuilder.append("(");
			stringBuilder.append(productVariantId);
			stringBuilder.append(",");
			stringBuilder.append(id);
			stringBuilder.append(")");
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		/* INSERT INTO tbl_name (a,b,c) VALUES(1,2,3),(4,5,6),(7,8,9); */
		return stringBuilder.toString();
	}

	public String buildGetAllOrderSql(CommonFilter commonFilter) {

		StringBuilder sql = new StringBuilder("");
		if (commonFilter.getOrderStatus() != null) {
			sql.append(" where order_status = '");
			sql.append(commonFilter.getOrderStatus().toString());
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
