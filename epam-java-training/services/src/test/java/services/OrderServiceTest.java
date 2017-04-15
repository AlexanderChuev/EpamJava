package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.dao.api.filters.SortData;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.services.IUserService;

public class OrderServiceTest extends AbstractTest {

	@Inject
	private IOrderService orderService;

	@Inject
	private IUserService userService;

	@Test
	public void test() {
		Assert.notNull(orderService);
		Assert.notNull(userService);
	}

	@Test
	public void getAllTest() {

		orderService.save(createOrder());
		orderService.save(createOrder());

		List<Order> allOrders = orderService.getAll(new OrderFilter());

		for (Order order : allOrders) {
			Assert.notNull(order);
			Assert.notNull(order.getId());
			Assert.notNull(order.getOrderStatus());
			Assert.notNull(order.getTotalPrice());
			Assert.notNull(order.getCreated());
			Assert.notNull(order.getUserProfileId());
		}
	}

	@Test
	public void getAllWithFilterTest() {

		orderService.save(createOrder());
		orderService.save(createOrder());

		SortData sortData = new SortData();
		sortData.setColumn("created");
		sortData.setDirection("desc");

		OrderFilter orderFilter = new OrderFilter();
		orderFilter.setOrderStatus(OrderStatus.BASKET);
		orderFilter.setSort(sortData);

		List<Order> allOrders = orderService.getAll(orderFilter);

		for (Order order : allOrders) {
			Assert.notNull(order);
			Assert.notNull(order.getId());
			Assert.notNull(order.getOrderStatus());
			Assert.notNull(order.getTotalPrice());
			Assert.notNull(order.getCreated());
			Assert.notNull(order.getUserProfileId());
		}
	}

	@Test
	public void getAndSaveTest() {

		Order order = createOrder();

		Integer orderId = orderService.save(order);
		Assert.notNull(orderId);

		Order orderFromDb = orderService.get(orderId);

		Assert.notNull(orderFromDb);
		Assert.notNull(orderFromDb.getId());
		Assert.notNull(orderFromDb.getOrderStatus());
		Assert.notNull(orderFromDb.getTotalPrice());
		Assert.notNull(orderFromDb.getCreated());
		Assert.notNull(orderFromDb.getUserProfileId());

		System.out.println(orderFromDb.getCreated());
		System.out.println(order.getCreated());
		Assert.isTrue(orderFromDb.getCreated().equals(order.getCreated()), "A");

		Assert.isTrue(orderFromDb.getTotalPrice().equals(order.getTotalPrice()), "B");
		Assert.isTrue(orderFromDb.getUserProfileId().equals(order.getUserProfileId()), "C");
		Assert.isTrue(orderFromDb.getOrderStatus().equals(order.getOrderStatus()), "D");
	}

	@Test
	public void updateTest() {

		Integer orderId = orderService.save(createOrder());
		Order orderFromDb = orderService.get(orderId);

		orderFromDb.setOrderStatus(OrderStatus.IN_PROCESSING);
		orderService.update(orderFromDb);

		Order updatedOrder = orderService.get(orderId);

		Assert.notNull(updatedOrder);
		Assert.notNull(updatedOrder.getId());
		Assert.notNull(updatedOrder.getOrderStatus());
		Assert.notNull(updatedOrder.getTotalPrice());
		Assert.notNull(updatedOrder.getCreated());
		Assert.notNull(updatedOrder.getUserProfileId());

		Assert.isTrue(updatedOrder.getId().equals(orderFromDb.getId()));
		Assert.isTrue(updatedOrder.getCreated().equals(orderFromDb.getCreated()));
		Assert.isTrue(updatedOrder.getTotalPrice().equals(orderFromDb.getTotalPrice()));
		Assert.isTrue(updatedOrder.getUserProfileId().equals(orderFromDb.getUserProfileId()));

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {
		
		Integer orderId = orderService.save(createOrder());
		orderService.delete(orderId);
		orderService.get(orderId);
	}

	private Order createOrder() {

		UserCredentials userCredentials = createUserCredentials();
		UserProfile userProfile = createUserProfile();

		UserProfile savedUser = userService.saveUser(userProfile, userCredentials);

		Order order = new Order();
		order.setCreated(new Date());
		order.setOrderStatus(OrderStatus.BASKET);
		order.setTotalPrice(50d);
		order.setUserProfileId(savedUser.getId());

		return order;
	}
}
