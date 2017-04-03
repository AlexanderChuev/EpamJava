package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;

public class AttributeServiceTest extends AbstractTest {

	@Inject
	private IAttributeService attributeService;
	
	@Test
	public void getNamesTest() {
		List<String> names = attributeService.getNames();
		
		for (String string : names) {
			System.out.println(string);
		}
	}

	@Test
	public void getIdTest() {

		/*Attribute attributeFromDb = attributeService.get(1);

		Assert.notNull(attributeFromDb, "The attribute must not be null");

		Assert.notNull(attributeFromDb.getAttributeName(), "The name attributeFromDb must not be null");

		Assert.notNull(attributeFromDb.getValue(), "The value attributeFromDb must not be null");*/

		/* System.out.println(attributeFromDb); */

	}

	@Test
	public void saveOrUpdateTest() {

		Attribute attribute = new Attribute();
		attribute.setName("Size");
		attribute.setValue(String.valueOf(new Date().getTime()));

		Attribute attributeFromDb = attributeService.saveOrUpdate(attribute);

		Assert.notNull(attributeFromDb, "The attribute must not be null");

		Assert.notNull(attributeFromDb.getName(), "The name attributeFromDb must not be null");

		Assert.notNull(attributeFromDb.getValue(), "The value attributeFromDb must not be null");

		Assert.isTrue(attribute.getName().equals(attributeFromDb.getName()),
				"Field values of the name must be equal");

		Assert.isTrue(attribute.getValue().equals(attributeFromDb.getValue()),
				"Field values of the value must be equal");

		/* System.out.println(attributeFromDb); */

	}

	@Test(expected = DuplicateKeyException.class)
	public void saveOrUpdateUniqueValueTest() {

		Attribute firstAttribute = new Attribute();
		Attribute secondAttribute = new Attribute();
		
		String attributeValue = "value" + new Date().getTime();
		
		firstAttribute.setName("attributeName");
		firstAttribute.setValue(attributeValue);
		
		secondAttribute.setName("attributeName");
		secondAttribute.setValue(attributeValue);

		attributeService.saveOrUpdate(firstAttribute);
		attributeService.saveOrUpdate(secondAttribute);
	}

	/*@Test(expected = EmptyResultDataAccessException.class)
	public void deleteTest() {

		Attribute attribute = new Attribute();
		attribute.setAttributeName("Size");
		attribute.setValue(String.valueOf(new Date().getTime()));

		Attribute attributeFromDb = attributeService.saveOrUpdate(attribute);
		attributeService.delete(attributeFromDb.getId());
		attributeService.get(attributeFromDb.getId());

	}*/

}
