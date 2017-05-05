package com.chuyeu.training.myapp.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerExample {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerExample.class);
    
    public static void main(String[] args) {

       // LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
     //   LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
        
        
    }
}
