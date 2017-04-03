package services;

import javax.inject.Inject;

import org.junit.Test;

import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.services.IOrderService;

public class OrderServiceTest extends AbstractTest {

	@Inject
	private IOrderService orderService;

	@Test
	public void getOrderByStatusTest() {

		System.out.println(orderService.getOrderByStatus(1, OrderStatus.BASKET));
	}
}
