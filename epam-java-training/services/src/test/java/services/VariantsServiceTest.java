package services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.IVariantsService;

public class VariantsServiceTest extends AbstractTest{

	@Inject
	private IVariantsService variantsService;
	
	@Inject
	private IProductService productService;
	
	@Inject
	private IProductVariantService productVariantsService;
	
	@Inject
	private IAttributeService attributeService;
	
	@Test
	@Rollback(false)
	public void add(){
		
		Product product = createProduct();
		
		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		
		Attribute attribute2 = createAttribute();
		attributeService.add(attribute2);
		
		Integer attributeId = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		Integer attributeId2 = attributeService.getIdByNameAndValue(attribute2.getName(), attribute2.getValue());
		
		Integer productId = productService.add(product);
		
		ProductVariant productVariant = createProductVariant(productId);
		productVariantsService.saveOrUpdate(productVariant);
		
		Integer productVariantId = productVariantsService.getAllByProduct(productId).get(0).getProductVariant().getId();
		
		List<Integer> list = new ArrayList<>();
		list.add(attributeId);
		list.add(attributeId2);
		variantsService.add(productVariantId, list);
		
	}
	
	@Test
	public void delete(){
		/*variantsService.delete(id);
		
		variantsService.delete(listId);*/
	}
	
}
