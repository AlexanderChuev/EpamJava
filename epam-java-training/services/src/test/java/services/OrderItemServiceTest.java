package services;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;

public class OrderItemServiceTest extends AbstractTesst {

	@Test
	public void test() {
		Assert.notNull(orderItemService, "The orderItemService must not be null");
		Assert.notNull(productService, "The productService must not be null");
		Assert.notNull(productVariantService, "The productVariantService must not be null");
		Assert.notNull(userService, "The userService must not be null");
		Assert.notNull(orderService, "The orderService must not be null");
	}

	@Test
	public void getAllByOrderIdTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		Integer orderId = getOrderId();
		orderItem.setOrderId(orderId);

		orderItemService.saveOrUpdate(orderItem);

		List<OrderItem> allOrderItems = orderItemService.getAllByOrderId(orderId);
		Assert.notNull(allOrderItems, "The list of allOrderItems must not be null");

		for (OrderItem orderItemFromDb : allOrderItems) {
			Assert.notNull(orderItemFromDb, "The orderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getId(), "ID from OrderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getProductVariantId(),
					"ProductVariantId from OrderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getOrderQuantity(), "OrderQuantity from OrderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getOrderId(), "OrderId from OrderItemFromDb must not be null");
		}
	}

	@Test
	public void getTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		Integer orderId = getOrderId();
		orderItem.setOrderId(orderId);

		orderItemService.saveOrUpdate(orderItem);

		OrderItem orderItemFromDb = orderItemService.getAllByOrderId(orderId).get(0);
		OrderItem orderItemFromDb2 = orderItemService.get(orderItemFromDb.getId());

		Assert.notNull(orderItemFromDb2, "The orderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getId(), "ID from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getProductVariantId(),
				"ProductVariantId from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getOrderQuantity(), "OrderQuantity from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getOrderId(), "OrderId from OrderItemFromDb must not be null");

		Assert.isTrue(orderItemFromDb2.getProductVariantId().equals(orderItem.getProductVariantId()),
				"ProductVariantIds from orderItems not equals");
		Assert.isTrue(orderItemFromDb2.getOrderQuantity().equals(orderItem.getOrderQuantity()),
				"OrdersQuantity from ordersItem not equals");
		Assert.isTrue(orderItemFromDb2.getOrderId().equals(orderItem.getOrderId()),
				"OrderQuantity from orderItems not equals");
	}

	@Test
	public void saveOrUpdateTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		Integer orderId = getOrderId();
		orderItem.setOrderId(orderId);

		orderItemService.saveOrUpdate(orderItem);
		List<OrderItem> orderItemsFromDb = orderItemService.getAllByOrderId(orderItem.getOrderId());
		OrderItem orderItemFromDb = orderItemsFromDb.get(orderItemsFromDb.size() - 1);

		Assert.notNull(orderItemFromDb, "The orderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb.getId(), "ID from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb.getProductVariantId(), "ProductVariantId from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb.getOrderQuantity(), "OrderQuantity from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb.getOrderId(), "OrderId from OrderItemFromDb must not be null");

		Assert.isTrue(orderItemFromDb.getProductVariantId().equals(orderItem.getProductVariantId()),
				"ProductVariantIds from orderItems not equals");
		Assert.isTrue(orderItemFromDb.getOrderQuantity().equals(orderItem.getOrderQuantity()),
				"OrdersQuantity from ordersItem not equals");
		Assert.isTrue(orderItemFromDb.getOrderId().equals(orderItem.getOrderId()),
				"OrderQuantity from orderItems not equals");
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		Integer orderId = getOrderId();
		orderItem.setOrderId(orderId);

		orderItemService.saveOrUpdate(orderItem);
		
		List<OrderItem> orderItemsFromDb = orderItemService.getAllByOrderId(orderItem.getOrderId());
		OrderItem orderItemFromDb = orderItemsFromDb.get(orderItemsFromDb.size() - 1);
		
		orderItemService.delete(orderItemFromDb.getId());
		orderItemService.get(orderItemFromDb.getId());

	}

	private Integer getProductVariantId(){
		Product product = createProduct();
		Integer productId = productService.add(product);
		ProductVariant productVariant = createProductVariant(productId);
		productVariantService.save(productVariant);
		List<ProductVariant> allByProduct = productVariantService.getAllByProduct(productId);
		Integer productVariantId = allByProduct.get(0).getId();
		return productVariantId;
	}
	
	private Integer getOrderId() {

		UserProfile userProfile = createUserProfile();
		UserCredentials userCredentials = createUserCredentials();
		Integer registrationId = userService.registration(userProfile, userCredentials);

		Order order = new Order();
		order.setCreated(new Date());
		order.setUserProfileId(registrationId);
		order.setOrderStatus(OrderStatus.CART);
		return orderService.save(order);
	}

}
