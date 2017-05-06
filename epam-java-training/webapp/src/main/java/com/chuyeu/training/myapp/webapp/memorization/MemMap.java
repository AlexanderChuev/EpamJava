package com.chuyeu.training.myapp.webapp.memorization;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MemMap {
	
	private static MemMap instance;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private Map<String, Date> timeMap = new HashMap<String, Date>();
	private int storageTime = 60000;
	
	private MemMap() {
	}

	public static MemMap getInstance() {
		if (instance == null) {
			instance = new MemMap();
		}
		return instance;
	}

    public void putData(String req, Object ob) {
        dataMap.put(req,ob);
        timeMap.put(req, new Date());
    }
    
    public Object getData(String req, Date now) {
    	Date date = timeMap.get(req);
    	if(date!= null && now.getTime()-date.getTime() > storageTime){
    		dataMap.remove(req);
    		timeMap.remove(req);
    		return null;
    	}
        return dataMap.get(req);       
    }
    
}