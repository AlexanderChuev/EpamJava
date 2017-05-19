package services;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Product;

public class ProductServiceTest extends AbstractTesst {

	@Test
	public void test() {
		Assert.notNull(productService, "The productService must not be null");
	}

	public void addAndGetTest() {

		Product product = createProduct();
		Integer id = productService.add(product);

		Assert.notNull(id, "The product id from DB must not be null");

		Product productFromDb = productService.get(id);

		Assert.notNull(productFromDb, "The product from DB must not be null");
		Assert.notNull(productFromDb.getNameRu(), "The product name from DB must not be null");
		Assert.notNull(productFromDb.getNameEn(), "The product name from DB must not be null");
		Assert.notNull(productFromDb.getBasePrice(), "The initial base price from DB must not be null");
		Assert.notNull(productFromDb.getActive(), "The active product from DB must not be null");
		Assert.isTrue(productFromDb.getNameRu().equals(product.getNameRu()), "Fields must be equal (name)");
		Assert.isTrue(productFromDb.getDescriptionRu().equals(product.getDescriptionRu()),
				"Fields must be equal (description)");
		Assert.isTrue(productFromDb.getNameEn().equals(product.getNameEn()), "Fields must be equal (name)");
		Assert.isTrue(productFromDb.getDescriptionEn().equals(product.getDescriptionEn()),
				"Fields must be equal (description)");
		Assert.isTrue(productFromDb.getBasePrice().equals(product.getBasePrice()), "Fields must be equal (price)");
		Assert.isTrue(productFromDb.getActive().equals(product.getActive()), "Fields must be equal (active)");

	}

	@Test
	public void updateTest() {

		Product product = createProduct();
		Integer id = productService.add(product);

		Assert.notNull(id, "The product id from DB must not be null");
		product.setId(id);

		productService.update(changeProduct(product));
		Product productFromDb = productService.get(id);

		Assert.notNull(productFromDb, "The product from DB must not be null");
		Assert.notNull(productFromDb.getNameRu(), "The product name from DB must not be null");
		Assert.notNull(productFromDb.getNameEn(), "The product name from DB must not be null");
		Assert.notNull(productFromDb.getBasePrice(), "The initial base price from DB must not be null");
		Assert.notNull(productFromDb.getActive(), "The active product from DB must not be null");
		Assert.isTrue(productFromDb.getNameRu().equals(product.getNameRu()), "Fields must be equal (name)");
		Assert.isTrue(productFromDb.getDescriptionRu().equals(product.getDescriptionRu()),
				"Fields must be equal (description)");
		Assert.isTrue(productFromDb.getNameEn().equals(product.getNameEn()), "Fields must be equal (name)");
		Assert.isTrue(productFromDb.getDescriptionEn().equals(product.getDescriptionEn()),
				"Fields must be equal (description)");
		Assert.isTrue(productFromDb.getBasePrice().equals(product.getBasePrice()), "Fields must be equal (price)");
		Assert.isTrue(productFromDb.getActive().equals(product.getActive()), "Fields must be equal (active)");

	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {
		Product product = createProduct();
		Integer id = productService.add(product);
		productService.delete(id);
		productService.get(id);
	}

	@Test
	public void getAllTest() {

		productService.add(createProduct());
		Integer productQuantity = productService.getProductQuantity();
		Assert.notNull(productQuantity, "The product quantity must not be null");
		Integer limit = 2;

		Integer pageCount = (int) Math.ceil((double) productQuantity / limit);

		for (int i = 1; i <= pageCount; i++) {

			Integer pageNumber = pageCount;
			CommonFilter commonFilter = new CommonFilter(pageNumber, limit, "name_ru", "desc");

			List<Product> products = productService.getAll(commonFilter);

			Assert.notNull(products, "The list of products must not be null");
			Assert.notEmpty(products, "The list of products must not be empty");

			for (Product product : products) {
				Assert.notNull(product, "The product from DB must not be null");
				Assert.notNull(product.getNameRu(), "The product name from DB must not be null");
				Assert.notNull(product.getNameEn(), "The product name from DB must not be null");
				Assert.notNull(product.getBasePrice(), "The initial base price from DB must not be null");
				Assert.notNull(product.getActive(), "The active product from DB must not be null");
			}
		}
	}

	@Test
	public void getProductQuantityTest() {
		Product product = createProduct();
		productService.add(product);
		Integer productQuantity = productService.getProductQuantity();
		Assert.notNull(productQuantity, "Product quantity must not be null");
	}

	private Product changeProduct(Product product) {
		product.setNameRu("Adidas" + new Date().getTime());
		product.setDescriptionRu("Вьетнамские кроссовки");
		product.setBasePrice((double) 60);
		product.setActive(false);
		return product;
	}

}
