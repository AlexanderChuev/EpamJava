package com.chuyeu.training.myapp.webapp.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;

@RestController
@RequestMapping("/attribute")
public class AttributeController {
	
	/*@Inject
	private IAttributeService attributeService;
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getNames(@PathVariable(value = "id") Integer id) {
    	List<String> names = attributeService.getNames();
        return new ResponseEntity<List<String>>(names, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getValuesByName(@PathVariable(value = "id") Integer bookIdParam) {
    	List<String> valuesByName = attributeService.getValuesByName("");
        return new ResponseEntity<List<String>>(valuesByName, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getIdByNameAndValue(@PathVariable(value = "id") Integer bookIdParam) {
    	Integer id = attributeService.getIdByNameAndValue("", "");
        return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }
	
    
    
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewAttribute(@RequestBody AttributeModel attributeModel) {
		Attribute attribute = model2entity(attributeModel);
		attributeService.add(attribute);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	

	
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAttributeValue(@PathVariable(value = "id") Integer id) {
    	attributeService.deleteAttributeValue(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAttribute(@PathVariable(value = "id") Integer id) {
    	attributeService.delete("");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    
	private AttributeModel entity2model(Attribute attribute) {
		AttributeModel model = new AttributeModel();
		model.setName(attribute.getName());
		model.setValue(attribute.getValue());
        return model;
    }
	
	private Attribute model2entity(AttributeModel model) {
		Attribute attribute = new Attribute();
		attribute.setName(model.getName());
		attribute.setValue(model.getValue());
        return attribute;
    }*/
}
