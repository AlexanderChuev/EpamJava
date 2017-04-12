package services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.Product;
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
	
	/*@Test
	@Rollback(false)
	public void add(){
		
		Product product = new Product();
		product.setName("Adidas");
		product.setDescription("Very cool shoes");
		product.setStartingPrice(50.0);
		product.setActive(true);
		
		Product productFromDb = productService.saveOrUpdate(product);
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		variantsService.add(1, list);
		
	}*/
	
	@Test
	public void delete(){
		/*variantsService.delete(id);
		
		variantsService.delete(listId);*/
	}
	
}
