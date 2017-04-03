package com.chuyeu.training.myapp.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chuyeu.training.myapp.services.IAttributeService;

public class Runner {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("web-context.xml");
		
		IAttributeService service = contex.getBean(IAttributeService.class);
		
	 	System.out.println(service.getIdByNameAndValue("Color", "Blue"));


		/*IUserCredentialsService service = contex.getBean(IUserCredentialsService.class);*/

		// System.out.println(service.get(1));

		/*
		 * IOrdersDao service = contex.getBean(IOrdersDao.class);
		 * IUserProfileDao service2 = contex.getBean(IUserProfileDao.class);
		 * UserProfile userProfile = service2.get(1);
		 * 
		 * 
		 * Order order = new Order(); order.setCreated(new Timestamp(new
		 * Date().getTime())); order.setUserProfile(userProfile);
		 * order.setTotalPrice(100.00);
		 * 
		 * service.insert(order);
		 */

		/*
		 * IOrdersDao service = contex.getBean(IOrdersDao.class);
		 * 
		 * Order order = new Order(); order.setCreated(new Timestamp(new
		 * Date().getTime())); order.setUserProfile();
		 * order.setTotalPrice(totalPrice);
		 * 
		 * service.insert(order); for (Orders orders : all) {
		 * System.out.println(orders); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Timestamp date = new Timestamp(new Date().getTime());
		 */

		/*
		 * UserCredentials uc = new UserCredentials(); uc.setId(4);
		 * uc.setEmail("ddd@mail.ru"); uc.setPassword("zzzzz");
		 * uc.setUserRole(UserRole.CLIENT);
		 * 
		 * service.update(uc);
		 */

		/*
		 * UserCredentials uc = new UserCredentials();
		 * uc.setEmail("ddd@mail.ru"); uc.setPassword("zasx");
		 * uc.setUserRole(UserRole.CLIENT);
		 * 
		 * System.out.println(service.insert(uc));
		 */

		contex.close();

	}

}
