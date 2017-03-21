package com.chuyeu.training.myapp.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

public class Runner {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");
		System.out.println(contex);
		
		IProductService iProductService = contex.getBean(IProductService.class);
		System.out.println(iProductService.get(1));
	}

}
