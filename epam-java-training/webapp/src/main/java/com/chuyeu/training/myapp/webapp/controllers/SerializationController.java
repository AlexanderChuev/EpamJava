package com.chuyeu.training.myapp.webapp.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.webapp.memorization.JsonData;
import com.chuyeu.training.myapp.webapp.memorization.Memoization;
import com.chuyeu.training.myapp.webapp.serialization.Serializator;

@RestController
@RequestMapping("/serialization")
public class SerializationController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SerializationController.class);
	
	@RequestMapping(value = "/{error}", method = RequestMethod.GET)
	public void save(@PathVariable(value = "error") String str) {
		if(str.toUpperCase().equals("ERROR")){
			Serializator serializator = new Serializator();
			Map<String, JsonData> dataMap = Memoization.getInstance().getDataMap();
			serializator.serialization(dataMap);
			/*for (Map.Entry<String, JsonData> entry : dataMap.entrySet()) {
				 System.out.println(entry.getKey() + "/" + entry.getValue());
			}*/
			LOGGER.error("Saving a map with search results");
		}
	}
}
