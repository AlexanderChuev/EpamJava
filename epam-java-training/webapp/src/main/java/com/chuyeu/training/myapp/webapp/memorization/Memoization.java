package com.chuyeu.training.myapp.webapp.memorization;

import java.io.InvalidObjectException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chuyeu.training.myapp.webapp.serialization.Serializator;

public class Memoization {

	private final Logger LOGGER = LoggerFactory.getLogger(Memoization.class);
	private static Memoization instance;
	private Map<String, JsonData> dataMap = new HashMap<String, JsonData>();
	private int storageTime = 180000;

	private Memoization() {
		Serializator serializator = new Serializator();
		try {
			this.dataMap = serializator.deserialization();
		} catch (InvalidObjectException e) {
			LOGGER.info("Map not found");
		}
		/*for (Map.Entry<String, JsonData> entry : dataMap.entrySet()) {
			 System.out.println(entry.getKey() + "/" + entry.getValue());
		}*/
	}

	public static Memoization getInstance() {
		if (instance == null) {
			instance = new Memoization();
		}
		return instance;
	}

	public void putData(String path, Object ob) {
		JsonData jsonData = new JsonData(ob);
		dataMap.put(path, jsonData);
	}

	public Object getData(String path, Date now) {

		if (dataMap.get(path) == null) {
			return null;
		}

		Date date = dataMap.get(path).getDate();
		if (date != null && now.getTime() - date.getTime() > storageTime) {
			dataMap.remove(path);
			return null;
		}
		return dataMap.get(path).getData();
	}

	public Map<String, JsonData> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, JsonData> dataMap) {
		this.dataMap = dataMap;
	}

}
