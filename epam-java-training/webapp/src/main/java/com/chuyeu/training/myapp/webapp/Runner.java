package com.chuyeu.training.myapp.webapp;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.services.IOrderItemService;

public class Runner {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");
		
		IOrderItemService service = contex.getBean(IOrderItemService.class);
		
		List<OrderItem> all = service.getAll();
		
		for (OrderItem orderItem : all) {
			System.out.println(orderItem);
		}
		
	//	System.out.println(service.get(1));
		
		
		/*IOrdersDao service = contex.getBean(IOrdersDao.class);
		IUserProfileDao service2 = contex.getBean(IUserProfileDao.class);
		UserProfile userProfile = service2.get(1);
		
		
		Order order = new Order();
		order.setCreated(new Timestamp(new Date().getTime()));
		order.setUserProfile(userProfile);
		order.setTotalPrice(100.00);
		
		service.insert(order);*/
	
		
/*		IOrdersDao service = contex.getBean(IOrdersDao.class);
		
		Order order = new Order();
		order.setCreated(new Timestamp(new Date().getTime()));
		order.setUserProfile();
		order.setTotalPrice(totalPrice);
		
		service.insert(order);
		for (Orders orders : all) {
			System.out.println(orders);	
		}
		
		
		
		
		
		
		
		Timestamp date = new Timestamp(new Date().getTime());*/
		
		
		/*UserCredentials uc = new UserCredentials();
		uc.setId(4);
		uc.setEmail("ddd@mail.ru");
		uc.setPassword("zzzzz");
		uc.setUserRole(UserRole.CLIENT);
		
		service.update(uc);
		*/
		
		
		/*UserCredentials uc = new UserCredentials();
		uc.setEmail("ddd@mail.ru");
		uc.setPassword("zasx");
		uc.setUserRole(UserRole.CLIENT);
		
		System.out.println(service.insert(uc));*/
		
	
		
		
		/*for (ProductVariant productVariant : all) {
			System.out.println(productVariant);
		}*/
		
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
		
		contex.close();
		
	}

}
