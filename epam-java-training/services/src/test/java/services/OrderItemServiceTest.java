package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IOrderItemService;

public class OrderItemServiceTest extends AbstractTest{

	@Inject
	private IOrderItemService orderItemService;

	/*@Test
	public void getAllTest() {

		List<orderItemService> attributes = orderItemService.getAll();

		Assert.notNull(attributes);
	}

	@Test
	public void selectTest() {

		Attribute attributeFromDb = orderItemService.get(1);

		Assert.notNull(attributeFromDb, "The attribute must not be null");

		Assert.notNull(attributeFromDb.getAttributeName(), "The name attributeFromDb must not be null");

		Assert.notNull(attributeFromDb.getValue(), "The value attributeFromDb must not be null");

	}

	@Test
	public void saveOrUpdateTest() {

		Attribute attribute = new Attribute();
		attribute.setAttributeName("Size");
		attribute.setValue(String.valueOf(new Date().getTime()));
		
		
		Attribute attributeFromDb = attributeService.saveOrUpdate(attribute);

		Assert.notNull(attributeFromDb, "The attribute must not be null");

		Assert.notNull(attributeFromDb.getAttributeName(), "The name attributeFromDb must not be null");

		Assert.notNull(attributeFromDb.getValue(), "The value attributeFromDb must not be null");

		Assert.isTrue(attribute.getAttributeName().equals(attributeFromDb.getAttributeName()),
				"Field values of the name must be equal");

		Assert.isTrue(attribute.getValue().equals(attributeFromDb.getValue()),
				"Field values of the value must be equal");

	}
	
	@Test(expected=DuplicateKeyException.class)
	public void saveOrUpdateUniqueValueTest() {
		
		Attribute attribute = new Attribute();
		attribute.setAttributeName("Size");
		attribute.setValue("M");
		
		attributeService.saveOrUpdate(attribute);
		attributeService.saveOrUpdate(attribute);
	}
	

	@Test(expected=EmptyResultDataAccessException.class)
	public void deleteTest(){

		Attribute attribute = new Attribute();
		attribute.setAttributeName("Size");
		attribute.setValue(String.valueOf(new Date().getTime()));

		Attribute attributeFromDb = attributeService.saveOrUpdate(attribute);
		attributeService.delete(attributeFromDb.getId());
		attributeService.get(attributeFromDb.getId());

	}*/
	
}
