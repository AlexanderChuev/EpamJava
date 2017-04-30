package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
import com.chuyeu.training.myapp.services.IOrderItemService;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.IUserService;

public class OrderItemServiceTest extends AbstractTesst{

	
	@Test
	public void test() {
		Assert.notNull(orderItemService, "The orderItemService must not be null");
		Assert.notNull(productService,"The productService must not be null");
		Assert.notNull(productVariantService,"The productVariantService must not be null");
		Assert.notNull(userService,"The userService must not be null");
		Assert.notNull(orderService,"The orderService must not be null");
	}

/*	@Test
	public void getAllByOrderIdTest() {

		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		orderItemService.saveOrUpdate(orderItem);

		List<OrderItem> allOrderItems = orderItemService.getAllByOrderId(orderItemfromDb.getOrderId());
		Assert.notNull(allOrderItems, "The list of allOrderItems must not be null");
		
		for (OrderItem orderItemFromDb : allOrderItems) {
			Assert.notNull(orderItemFromDb, "The orderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getId(), "ID from OrderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getProductVariantId(), "ProductVariantId from OrderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getOrderQuantity(), "OrderQuantity from OrderItemFromDb must not be null");
			Assert.notNull(orderItemFromDb.getOrderId(), "OrderId from OrderItemFromDb must not be null");
		}
	}
*/
	@Test
	public void getTest() {
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		orderItemService.saveOrUpdate(orderItem);
		List<OrderItem> orderItemsFromDb = orderItemService.getAllByOrderId(orderItem.getOrderId());
		
		OrderItem orderItemFromDb = orderItemsFromDb.get(0);
		OrderItem orderItemFromDb2 = orderItemService.get(orderItemFromDb.getId());
		
		Assert.notNull(orderItemFromDb2, "The orderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getId(), "ID from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getProductVariantId(), "ProductVariantId from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getOrderQuantity(), "OrderQuantity from OrderItemFromDb must not be null");
		Assert.notNull(orderItemFromDb2.getOrderId(), "OrderId from OrderItemFromDb must not be null");
		
		Assert.isTrue(orderItemFromDb2.getProductVariantId().equals(orderItem.getProductVariantId()), "ProductVariantIds from orderItems not equals");
		Assert.isTrue(orderItemFromDb2.getOrderQuantity().equals(orderItem.getOrderQuantity()), "OrdersQuantity from ordersItem not equals");
		Assert.isTrue(orderItemFromDb2.getOrderId().equals(orderItem.getOrderId()), "OrderQuantity from orderItems not equals");
	}
	
	
	
	/*@Test
	public void saveOrUpdateTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		orderItemService.saveOrUpdate(orderItem);

		Assert.notNull(orderItemfromDb, "The orderItemFromDb must not be null");
		Assert.notNull(orderItemfromDb.getId(), "ID from OrderItemFromDb must not be null");
		Assert.notNull(orderItemfromDb.getProductVariantId(), "ProductVariantId from OrderItemFromDb must not be null");
		Assert.notNull(orderItemfromDb.getOrderQuantity(), "OrderQuantity from OrderItemFromDb must not be null");
		Assert.notNull(orderItemfromDb.getOrderId(), "OrderId from OrderItemFromDb must not be null");
		
		Assert.isTrue(orderItemfromDb.getProductVariantId().equals(orderItem.getProductVariantId()), "ProductVariantIds from orderItems not equals");
		Assert.isTrue(orderItemfromDb.getOrderQuantity().equals(orderItem.getOrderQuantity()), "OrdersQuantity from ordersItem not equals");
		Assert.isTrue(orderItemfromDb.getOrderId().equals(orderItem.getOrderId()), "OrderQuantity from orderItems not equals");
	}	

	@Test(expected=EmptyResultDataAccessException.class)
	public void deleteTest(){
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		orderItemService.saveOrUpdate(orderItem);
		
		orderItemService.delete(orderItemfromDb.getId());
		orderItemService.get(orderItemfromDb.getId());
		
	}*/
	
	private Integer getProductVariantId(){
		Product product = createProduct();
		Integer productId = productService.add(product);
		ProductVariant productVariant = createProductVariant(productId);
		return productVariantService.saveOrUpdate(productVariant);
	} 
	
	private Integer getOrderId(){
		
		UserProfile userProfile = createUserProfile();
		UserCredentials userCredentials = createUserCredentials();
		UserProfile user = userService.registration(userProfile, userCredentials);
		
		Order order = new Order();
		order.setCreated(new Date());
		order.setUserProfileId(user.getId());
		order.setTotalPrice(50d); // посчитать
		order.setOrderStatus(OrderStatus.BASKET);
		return orderService.save(order);
	}
	
}
