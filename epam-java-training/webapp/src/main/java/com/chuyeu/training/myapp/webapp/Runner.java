package com.chuyeu.training.myapp.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chuyeu.training.myapp.services.IAttributeService;

public class Runner {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");
		
		IAttributeService service = contex.getBean(IAttributeService.class);
		
	 	System.out.println(service.getIdByNameAndValue("Color", "Blue"));

		contex.close();

	}

}
