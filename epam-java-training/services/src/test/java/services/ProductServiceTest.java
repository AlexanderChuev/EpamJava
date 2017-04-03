package services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

public class ProductServiceTest extends AbstractTest {

	@Inject
	private IProductService productService;

	@Test
	public void getAllTest() {

		List<Product> products = productService.getAll();

		Assert.notNull(products);

		/*
		 * for (Product product : products) { System.out.println(product); }
		 */

	}

	@Test
	public void getByIdTest() {

		Product productFromDb = productService.get(1);

		Assert.notNull(productFromDb, "The product from DB must not be null");

		Assert.notNull(productFromDb.getName(), "The name product from DB must not be null");

		Assert.notNull(productFromDb.getStartingPrice(), "The starting price product from DB must not be null");

		Assert.notNull(productFromDb.getActive(), "The active product from DB must not be null");

		/* System.out.println(productFromDb); */

	}

	@Test
	public void saveOrUpdateTest() {

		Product product = new Product();
		product.setName("Saucony");
		product.setDescription("China shoes");
		product.setStartingPrice((double) 50);
		product.setActive(true);

		Product productFromDb = productService.saveOrUpdate(product);

		Assert.notNull(productFromDb, "The product from DB must not be null");

		Assert.notNull(productFromDb.getName(), "The name product from DB must not be null");

		Assert.notNull(productFromDb.getStartingPrice(), "The starting price product from DB must not be null");

		Assert.notNull(productFromDb.getActive(), "The active product from DB must not be null");

		Assert.isTrue(productFromDb.getName().equals(product.getName()), "Field values of the name must be equal");

		Assert.isTrue(productFromDb.getDescription().equals(product.getDescription()),
				"Field values of the value must be equal");

		Assert.isTrue(productFromDb.getStartingPrice().equals(product.getStartingPrice()),
				"Field values of the value must be equal");

		Assert.isTrue(productFromDb.getActive().equals(product.getActive()), "Field values of the value must be equal");

		/* System.out.println(productFromDb); */

	}

}
