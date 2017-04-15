package services;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:services-context.xml")
@Transactional
public class AbstractTesst {

	public Product createProduct() {

		Product product = new Product();
		product.setName("Saucony" + new Date().getTime());
		product.setDescription("China shoes");
		product.setStartingPrice((double) 50);
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
		userCredentials.setEmail("emael" + new Date().getTime());
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
