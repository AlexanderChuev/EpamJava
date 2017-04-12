package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class AttributeDaoXmlImpl implements IAttributeDao {

	private final XStream xstream = new XStream(new DomDriver());
	
	@Override
	public void add(String name) {
	/*	File file = getFile();

        XmlModelWrapper<Integer, Book> wrapper = (XmlModelWrapper<Integer, Book>) xstream.fromXML(file);
        List<Book> books = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        book.setId(newId);
        books.add(book);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return book;*/
	}
	
	@Override
	public void add(Attribute attribute) {
		/*File file = getFile();

        XmlModelWrapper<Integer, Book> wrapper = (XmlModelWrapper<Integer, Book>) xstream.fromXML(file);
        List<Book> books = wrapper.getRows();
        Integer lastId = wrapper.getLastId();
        int newId = lastId + 1;

        book.setId(newId);
        books.add(book);

        wrapper.setLastId(newId);
        writeNewData(file, wrapper);
        return book;*/
	}

	@Override
	public void deleteAttributeValue(Integer id) {
		/*File file = getFile();

        XmlModelWrapper<Integer, Book> wrapper = (XmlModelWrapper<Integer, Book>) xstream.fromXML(file);
        List<Book> books = wrapper.getRows();
        Book found = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                found = book;
                break;
            }
        }
        if (found != null) {
            books.remove(found);
            writeNewData(file, wrapper);
        }*/
	}

	@Override
	public void delete(String name) {
	}

	@Override
	public List<Integer> listIdByName(String name) {
		return null;
	}

	@Override
	public List<Attribute> getProductVariantAttributes(Integer productVariantId) {
		return null;
	}

	@Override
	public List<String> getNames() {
		return null;
	}

	@Override
	public List<String> getValuesByName(String name) {
		return null;
	}

	@Override
	public Integer getIdByNameAndValue(String name, String value) {
		return null;
	}

	/*private void writeNewData(File file, XmlModelWrapper obj) {
        try {
            xstream.toXML(obj, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile() {
        File file = new File(rootFolder + "books.xml");
        return file;
    }*/
}
