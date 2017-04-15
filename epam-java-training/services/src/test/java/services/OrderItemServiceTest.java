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
import com.chuyeu.training.myapp.services.entity.ProductVariantEntity;

public class OrderItemServiceTest extends AbstractTest{

	@Inject
	private IOrderItemService orderItemService;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private IProductVariantService productVariantService;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IOrderService orderService;
	
	
	@Test
	public void test() {
		Assert.notNull(orderItemService);
		Assert.notNull(productService);
		Assert.notNull(productVariantService);
		Assert.notNull(userService);
		Assert.notNull(orderService);
	}

	@Test
	public void getAllByOrderIdTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		OrderItem orderItemfromDb = orderItemService.saveOrUpdate(orderItem);

		List<OrderItem> allOrderItems = orderItemService.getAllByOrderId(orderItemfromDb.getOrderId());
		Assert.notNull(allOrderItems);
		
		for (OrderItem orderItemFromDb : allOrderItems) {
			Assert.notNull(orderItemFromDb);
			Assert.notNull(orderItemFromDb.getId());
			Assert.notNull(orderItemFromDb.getProductVariantId());
			Assert.notNull(orderItemFromDb.getOrderQuantity());
			Assert.notNull(orderItemFromDb.getOrderId());
		}
	}

	@Test
	public void getTest() {
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		OrderItem orderItemfromDb = orderItemService.saveOrUpdate(orderItem);
		OrderItem orderItemfromDb2 = orderItemService.get(orderItemfromDb.getId());
		
		Assert.notNull(orderItemfromDb2);
		Assert.notNull(orderItemfromDb2.getId());
		Assert.notNull(orderItemfromDb2.getProductVariantId());
		Assert.notNull(orderItemfromDb2.getOrderQuantity());
		Assert.notNull(orderItemfromDb2.getOrderId());
		
		Assert.isTrue(orderItemfromDb2.getProductVariantId().equals(orderItem.getProductVariantId()));
		Assert.isTrue(orderItemfromDb2.getOrderQuantity().equals(orderItem.getOrderQuantity()));
		Assert.isTrue(orderItemfromDb2.getOrderId().equals(orderItem.getOrderId()));
	}
	
	
	
	@Test
	public void saveOrUpdateTest() {

		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		OrderItem orderItemfromDb = orderItemService.saveOrUpdate(orderItem);

		Assert.notNull(orderItemfromDb);
		Assert.notNull(orderItemfromDb.getId());
		Assert.notNull(orderItemfromDb.getProductVariantId());
		Assert.notNull(orderItemfromDb.getOrderQuantity());
		Assert.notNull(orderItemfromDb.getOrderId());
		
		Assert.isTrue(orderItemfromDb.getProductVariantId().equals(orderItem.getProductVariantId()));
		Assert.isTrue(orderItemfromDb.getOrderQuantity().equals(orderItem.getOrderQuantity()));
		Assert.isTrue(orderItemfromDb.getOrderId().equals(orderItem.getOrderId()));
	}	

	@Test(expected=EmptyResultDataAccessException.class)
	public void deleteTest(){
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(getProductVariantId());
		orderItem.setOrderQuantity(1);
		orderItem.setOrderId(getOrderId());
		
		OrderItem orderItemfromDb = orderItemService.saveOrUpdate(orderItem);
		
		orderItemService.delete(orderItemfromDb.getId());
		orderItemService.get(orderItemfromDb.getId());
		
	}
	
	private Integer getProductVariantId(){
		Product product = createProduct();
		Integer productId = productService.add(product);
		ProductVariant productVariant = createProductVariant(productId);
		productVariantService.saveOrUpdate(productVariant);
		List<ProductVariantEntity> productVariantEntity = productVariantService.getAllByProduct(productId);
		return productVariantEntity.get(0).getProductVariant().getId();
	}
	
	private Integer getOrderId(){
		
		UserProfile userProfile = createUserProfile();
		UserCredentials userCredentials = createUserCredentials();
		UserProfile user = userService.saveUser(userProfile, userCredentials);
		
		Order order = new Order();
		order.setCreated(new Date());
		order.setUserProfileId(user.getId());
		order.setTotalPrice(50d); // посчитать
		order.setOrderStatus(OrderStatus.BASKET);
		return orderService.save(order);
	}
	
}
