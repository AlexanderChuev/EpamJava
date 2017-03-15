package com.chuyeu.training.myapp.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");
		System.out.println(contex);
		
		String[] beanDefinitionNames = contex.getBeanDefinitionNames();
		
		for (String string : beanDefinitionNames) {
			System.out.println(string);
		}
	}

}
