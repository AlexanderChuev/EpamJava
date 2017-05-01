package services;

import java.util.Date;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IOrderItemService;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.services.IVariantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:services-context.xml")
//@Transactional
public class AbstractTesst {
	
	@Inject
	IProductService productService;
	
	@Inject
	IVariantService variantService;
	
	@Inject
	IAttributeService attributeService;
	
	@Inject
	IProductVariantService productVariantService;
	
	@Inject
	IOrderItemService orderItemService;
	
	@Inject
	IOrderService orderService;

	@Inject
	IUserService userService;

	public Product createProduct() {

		Product product = new Product();
		product.setName("Saucony" + new Date().getTime());
		product.setDescription("China shoes");
		product.setBasePrice((double) 50);
		product.setActive(true);
		return product;
	}

	public Attribute createAttribute() {

		Attribute attribute = new Attribute();

		String name = "Name" + new Date().getTime();
		String value = "Value" + new Date().getTime();

		attribute.setName(name);
		attribute.setValue(value);

		return attribute;
	}

	public ProductVariant createProductVariant(Integer productId) {

		ProductVariant productVariant = new ProductVariant();
		productVariant.setProductId(productId);
		productVariant.setAvailableQuantity(2);
		productVariant.setPriceInfluence(30d);
		return productVariant;
	}

	public UserCredentials createUserCredentials() {
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmail("email" + new Date().getTime());
		userCredentials.setPassword("password" + new Date().getTime());
		userCredentials.setUserRole(UserRole.CLIENT);
		return userCredentials;
	}

	public UserProfile createUserProfile() {

		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName("FirstName" + new Date().getTime());
		userProfile.setLastName("LastName" + new Date().getTime());
		return userProfile;
	}

}
