package services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;

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
		Integer productVariantId = productVariantService.saveOrUpdate(productVariant);
		Integer productVariantId2 = productVariantService.saveOrUpdate(productVariant);
		
		List<ProductVariant> allByProduct = productVariantService.getAllByProduct(productId);

		Assert.notNull(allByProduct, "List ProductVariantEntity must not be null");
		Assert.noNullElements(allByProduct.toArray(), "List ProductVariantEntity must not have null items");
		Assert.notEmpty(allByProduct, "List ProductVariantEntity must not be empty");

		ProductVariant productVariantFromDb = allByProduct.get(0);
		ProductVariant productVariantFromDb2 = allByProduct.get(1);
		
		Assert.isTrue(productVariantFromDb.getProductId().equals(productVariant.getProductId()),"");
	}

	@Test
	public void getProductVariantTest() {
		
		ProductVariant productVariant = createProductVariant();
		productVariantService.saveOrUpdate(productVariant);
		List<ProductVariantEntity> allByProduct = productVariantService.getAllByProduct(productVariant.getProductId());
		
		Integer id = allByProduct.get(0).getProductVariant().getId();
		ProductVariantEntity productVariantEntity = productVariantService.getProductVariant(id);
		
		checkProductVariantFromDb(productVariantEntity, productVariant);
	}

	@Test
	public void saveOrUpdateTest() {
		
		ProductVariant productVariant = createProductVariant();
		productVariantService.saveOrUpdate(productVariant);
		List<ProductVariantEntity> allByProduct = productVariantService.getAllByProduct(productVariant.getProductId());
		
		Integer productVariantId = allByProduct.get(0).getProductVariant().getId();
		ProductVariantEntity storedProductVariantEntity = productVariantService.getProductVariant(productVariantId);
		
		checkProductVariantFromDb(storedProductVariantEntity, productVariant);
		
		productVariant = createProductVariant();
		productVariant.setId(productVariantId);
		productVariantService.saveOrUpdate(productVariant);
		ProductVariantEntity updatedProductVariantEntity = productVariantService.getProductVariant(productVariantId);
		
		checkProductVariantFromDb(updatedProductVariantEntity, productVariant);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {
		ProductVariant productVariant = createProductVariant();
		productVariantService.saveOrUpdate(productVariant);
		
		List<ProductVariantEntity> allByProduct = productVariantService.getAllByProduct(productVariant.getProductId());
		Integer productVariantId = allByProduct.get(0).getProductVariant().getId();
		
		productVariantService.delete(productVariantId);
		productVariantService.getProductVariant(productVariantId);
		
	}
	
	public void checkProductVariantFromDb(ProductVariantEntity productVariantEntity, ProductVariant productVariant) {
		ProductVariant productVariantFromDb = productVariantEntity.getProductVariant();
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
