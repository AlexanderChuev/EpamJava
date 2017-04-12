package com.chuyeu.training.myapp.dao.util;

import java.util.List;

public class Converter {
	
	public String listIdToStringForDelete(List<Integer> listId){
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("(");
		for (Integer id : listId) {
			stringBuilder.append(id);
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append(")");
		return stringBuilder.toString();
		
	}
	
	public String listIdToStringForAdd(Integer productVariantId, List<Integer> listId){
		StringBuilder stringBuilder = new StringBuilder("");
		for (Integer id : listId) {
			stringBuilder.append("(");
			stringBuilder.append(productVariantId);
			stringBuilder.append(",");
			stringBuilder.append(id);
			stringBuilder.append(")");
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		/*INSERT INTO tbl_name (a,b,c) VALUES(1,2,3),(4,5,6),(7,8,9);*/
		return stringBuilder.toString();
	}
}
