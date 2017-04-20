package services;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.IVariantsService;

public class VariantsServiceTest extends AbstractTesst{

	@Inject
	private IVariantsService variantsService;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private IProductVariantService productVariantsService;
	
	@Inject
	private IAttributeService attributeService;

	@Test
	public void test() {
		Assert.notNull(variantsService, "The variantsService must not be null");
		Assert.notNull(productService, "The productService must not be null");
		Assert.notNull(productVariantsService, "The productVariantsService must not be null");
		Assert.notNull(attributeService, "The attributeService must not be null");
	}
	
	/*@Test
	public void addTest(){
	
		Integer productId = productService.add(createProduct());
		
		ProductVariant productVariant = createProductVariant(productId);
		productVariantsService.saveOrUpdate(productVariant);
		
		Integer productVariantId = productVariantsService.getAllByProduct(productId).get(0).getProductVariant().getId();
		
		List<Integer> list = new ArrayList<>();
		list.add(getSavedAttributeId());
		list.add(getSavedAttributeId());
		variantsService.add(productVariantId, list);
		
	}
	
	@Test
	public void deleteTest(){
		
		Integer productId = productService.add(createProduct());
		
		ProductVariant productVariant = createProductVariant(productId);
		productVariantsService.saveOrUpdate(productVariant);
		
		Integer productVariantId = productVariantsService.getAllByProduct(productId).get(0).getProductVariant().getId();
		
		Integer attributeId = getSavedAttributeId();
		
		List<Integer> list = new ArrayList<>();
		list.add(attributeId);
		variantsService.add(productVariantId, list);
		
		variantsService.delete(attributeId);
		variantsService.delete(attributeId);
	}
	
	private Integer getSavedAttributeId(){
		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		return attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}*/
	
}
