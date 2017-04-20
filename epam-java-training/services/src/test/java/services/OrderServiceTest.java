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

public class OrderServiceTest extends AbstractTesst {

	@Inject
	private IOrderService orderService;

	@Inject
	private IUserService userService;

	@Test
	public void test() {
		Assert.notNull(orderService, "The orderService must not be null");
		Assert.notNull(userService, "The userService must not be null");
	}

	@Test
	public void getAllTest() {

		orderService.save(createOrder());
		orderService.save(createOrder());

		List<Order> allOrders = orderService.getAll(new OrderFilter());

		for (Order order : allOrders) {
			checkOrder(order);
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
			checkOrder(order);
		}
	}

	@Test
	public void getAndSaveTest() {

		Order order = createOrder();

		Integer orderId = orderService.save(order);
		Assert.notNull(orderId, "Id from order must not be null");

		Order orderFromDb = orderService.get(orderId);
		
		checkOrder(orderFromDb);

		Assert.isTrue(orderFromDb.getCreated().equals(order.getCreated()), "The columns with the date must be equals");
		Assert.isTrue(orderFromDb.getTotalPrice().equals(order.getTotalPrice()), "The columns with the totalPrice must be equals");
		Assert.isTrue(orderFromDb.getUserProfileId().equals(order.getUserProfileId()), "The columns with the UserProfileId must be equals");
		Assert.isTrue(orderFromDb.getOrderStatus().equals(order.getOrderStatus()), "The columns with the OrderStatus must be equals");
	}

	@Test
	public void updateTest() {

		Integer orderId = orderService.save(createOrder());
		Order orderFromDb = orderService.get(orderId);

		orderFromDb.setOrderStatus(OrderStatus.IN_PROCESSING);
		orderService.update(orderFromDb);

		Order updatedOrder = orderService.get(orderId);

		checkOrder(updatedOrder);

		Assert.isTrue(updatedOrder.getId().equals(orderFromDb.getId()), "The columns with the id must be equals");
		Assert.isTrue(updatedOrder.getCreated().equals(orderFromDb.getCreated()), "The columns with the date must be equals");
		Assert.isTrue(updatedOrder.getTotalPrice().equals(orderFromDb.getTotalPrice()), "The columns with the totalPrice must be equals");
		Assert.isTrue(updatedOrder.getUserProfileId().equals(orderFromDb.getUserProfileId()), "The columns with the UserProfileId must be equals");

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {
		
		Integer orderId = orderService.save(createOrder());
		orderService.delete(orderId);
		orderService.get(orderId);
	}
	
	
	private void checkOrder(Order order){
		Assert.notNull(order, "The order must not be null");
		Assert.notNull(order.getId(), "Id from order must not be null");
		Assert.notNull(order.getOrderStatus(), "OrderStatus from order must not be null");
		Assert.notNull(order.getTotalPrice(), "TotalPrice from order must not be null");
		Assert.notNull(order.getCreated(), "Date from order must not be null");
		Assert.notNull(order.getUserProfileId(), "UserProfileId from order must not be null");
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
