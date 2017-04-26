package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.SortData;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;

public class ProductServiceTest extends AbstractTesst {

	@Inject
	private IProductService productService;

	@Test
	public void test() {
		Assert.notNull(productService,"The productService must not be null");
	}

	@Test
	public void addAndGetTest() {

		Product product = createProduct();
		Integer id = productService.add(product);

		Assert.notNull(id, "The product id from DB must not be null");

		Product productFromDb = productService.get(id);
		checkProducts(productFromDb, product);

	}

	@Test
	public void updateTest() {

		Product product = createProduct();
		Integer id = productService.add(product);

		Assert.notNull(id, "The product id from DB must not be null");
		product.setId(id);

		productService.update(changeProduct(product));
		Product productFromDb = productService.get(id);

		checkProducts(productFromDb, product);

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {
		Product product = createProduct();
		Integer id = productService.add(product);
		productService.delete(id);
		productService.get(id);
	}

/*	@Test
	public void getAllTest() {

		productService.add(createProduct());
		Integer productQuantity = productService.getProductQuantity();
		Assert.notNull(productQuantity, "The product quantity must not be null");
		
		ProductFilter productFilter = new ProductFilter();

		Integer limit = 5;

		Integer pageNumber = (int) Math.ceil((double) productQuantity / limit);

		productFilter.setLimit(limit);
		
		SortData sort = new SortData();
		sort.setColumn("name");
		productFilter.setSort(sort);

		for (int i = 1; i <= pageNumber; i++) {

			productFilter.setPageNumber(i);

			List<Product> products = productService.getAll(productFilter);

			Assert.notNull(products, "The list of products must not be null");
			Assert.notEmpty(products, "The list of products must not be empty");

			for (Product product : products) {
				checkProductFromDb(product);
			}
		}
	}*/

	

	private Product changeProduct(Product product) {
		product.setName("Adidas" + new Date().getTime());
		product.setDescription("Vietnam shoes");
		product.setBasePrice((double) 60);
		product.setActive(false);
		return product;
	}

	private void checkProducts(Product productFromDb, Product product) {

		checkProductFromDb(productFromDb);

		Assert.isTrue(productFromDb.getName().equals(product.getName()), "Fields must be equal (name)");
		Assert.isTrue(productFromDb.getDescription().equals(product.getDescription()),
				"Fields must be equal (description)");
		Assert.isTrue(productFromDb.getBasePrice().equals(product.getBasePrice()),
				"Fields must be equal (price)");
		Assert.isTrue(productFromDb.getActive().equals(product.getActive()), "Fields must be equal (active)");
	}

	private void checkProductFromDb(Product productFromDb) {
		Assert.notNull(productFromDb, "The product from DB must not be null");
		Assert.notNull(productFromDb.getName(), "The product name from DB must not be null");
		Assert.notNull(productFromDb.getBasePrice(), "The initial base price from DB must not be null");
		Assert.notNull(productFromDb.getActive(), "The active product from DB must not be null");
	}
}
