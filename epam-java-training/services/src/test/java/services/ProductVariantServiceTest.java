package services;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.ProductVariant;

public class ProductVariantServiceTest extends AbstractTesst {

	@Test
	public void test() {
		Assert.notNull(productVariantService, "The productVariantService must not be null");
		Assert.notNull(productService, "The productService must not be null");
		
	}

	@Test
	public void getAllByProductTest() {

		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		ProductVariant productVariant2 = createProductVariant(productId);
		productVariantService.saveOrUpdate(productVariant);
		productVariantService.saveOrUpdate(productVariant2);
		List<ProductVariant> allByProduct = productVariantService.getAllByProduct(productId);

		Assert.notNull(allByProduct, "List ProductVariantEntity must not be null");
		Assert.noNullElements(allByProduct.toArray(), "List ProductVariantEntity must not have null items");
		Assert.notEmpty(allByProduct, "List ProductVariantEntity must not be empty");

		ProductVariant productVariantFromDb = allByProduct.get(0);
		ProductVariant productVariantFromDb2 = allByProduct.get(1);
		
		Assert.isTrue(productVariantFromDb.getProductId().equals(productVariant.getProductId()),"");
		Assert.isTrue(productVariantFromDb.getAvailableQuantity().equals(productVariant.getAvailableQuantity()),"");
		Assert.isTrue(productVariantFromDb.getPriceInfluence().equals(productVariant.getPriceInfluence()),"");
		
		Assert.isTrue(productVariantFromDb2.getProductId().equals(productVariant2.getProductId()),"");
		Assert.isTrue(productVariantFromDb2.getAvailableQuantity().equals(productVariant2.getAvailableQuantity()),"");
		Assert.isTrue(productVariantFromDb2.getPriceInfluence().equals(productVariant2.getPriceInfluence()),"");
	}

	@Test
	public void getProductVariantTest() {
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId= productVariantService.saveOrUpdate(productVariant);
		
		ProductVariant productVariantFromDb = productVariantService.getProductVariant(productVariantId);
		checkProductVariantFromDb(productVariantFromDb, productVariant);
	}

	@Test
	public void updateTest() {
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId= productVariantService.saveOrUpdate(productVariant);
		ProductVariant productVariantFromDb = productVariantService.getProductVariant(productVariantId);
		productVariantFromDb.setPriceInfluence(12d);
		productVariantFromDb.setAvailableQuantity(12);
		Integer updetedProductVariantId= productVariantService.saveOrUpdate(productVariantFromDb);
		
		Assert.isTrue(productVariantId.equals(updetedProductVariantId),"");
		
		ProductVariant updatedProductVariantFromDb = productVariantService.getProductVariant(updetedProductVariantId);
		Assert.isTrue(productVariantFromDb.getProductId().equals(updatedProductVariantFromDb.getProductId()),"");
		Assert.isTrue(productVariantFromDb.getPriceInfluence().equals(updatedProductVariantFromDb.getPriceInfluence()),"");
		Assert.isTrue(productVariantFromDb.getAvailableQuantity().equals(updatedProductVariantFromDb.getAvailableQuantity()),"");
		
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId= productVariantService.saveOrUpdate(productVariant);
		
		productVariantService.delete(productVariantId);
		productVariantService.getProductVariant(productVariantId);
	}
	
	public void checkProductVariantFromDb(ProductVariant productVariantFromDb, ProductVariant productVariant) {
		
		Assert.notNull(productVariantFromDb, "productVariantFromDb must be saved");
		Assert.notNull(productVariantFromDb.getId(), "Field 'id' from productVariantFromDb must not be null");
		Assert.notNull(productVariantFromDb.getProductId(), "Field 'productId' from productVariantFromDb must not be null");
		Assert.notNull(productVariantFromDb.getPriceInfluence(), "Field 'priceInfluence' from productVariantFromDb must not be null");
		Assert.notNull(productVariantFromDb.getAvailableQuantity(), "Field 'availableQuantity' from productVariantFromDb must not be null");

		if (productVariant.getId()!=null){
			Assert.isTrue(productVariantFromDb.getId().equals(productVariant.getId()), "Fields (id) from productVariantFromDb must be equal");
		}
		
		Assert.isTrue(productVariantFromDb.getProductId().equals(productVariant.getProductId()), "Fields 'productId' from productVariantFromDb must be equal");
		Assert.isTrue(productVariantFromDb.getPriceInfluence().equals(productVariant.getPriceInfluence()), "Fields 'priceInfluence' from productVariantFromDb must be equal");
		Assert.isTrue(productVariantFromDb.getAvailableQuantity().equals(productVariant.getAvailableQuantity()), "Fields 'availableQuantity' from productVariantFromDb must be equal");
	}

}
