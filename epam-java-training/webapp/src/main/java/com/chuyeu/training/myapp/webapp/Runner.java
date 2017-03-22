package com.chuyeu.training.myapp.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

public class Runner {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");
		
		IProductService service = contex.getBean(IProductService.class);
		
		service.delete(6);
		
		/*Product product = new Product();
		product.setName("Kelme");
		product.setDescription("Belorusian shoes");
		product.setStartingPrice((double) 50);
		product.setActive(true);*/
		
		/*Product product = new Product();
		product.setId(7);
		product.setName("Saucony");
		product.setDescription("China shoes");
		product.setStartingPrice((double) 50);
		product.setActive(true);
		
		System.out.println(service.update(product));*/
		
	}

}
