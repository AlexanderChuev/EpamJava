package com.chuyeu.training.myapp.services.util;

import java.util.List;

public class Converter {
	
	public String listIdToString(List<Integer> listId){
		StringBuilder stringBuilder = new StringBuilder("");
		for (Integer id : listId) {
			stringBuilder.append(id);
			stringBuilder.append(",");
		}
		String stringListId = stringBuilder.toString();
		return stringListId.substring(0, stringListId.length()-1);
		
	}
}
