package services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
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
		Assert.notNull(variantsService);
		Assert.notNull(productService);
		Assert.notNull(productVariantsService);
		Assert.notNull(attributeService);
	}
	
	@Test
	//@Rollback(false)
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
	@Rollback(false)
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
	}
	
}
