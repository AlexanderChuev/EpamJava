package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserCredentialsDao;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class UserCredentialsDaoXmlImpl implements IUserCredentialsDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	@Override
	public List<UserCredentials> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserCredentials get(Integer id) {
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserCredentials> wrapper = (XmlModelWrapper<UserCredentials>) xstream.fromXML(file);
		List<UserCredentials> userCredentialsFromDb = wrapper.getRows();
		for (UserCredentials userCredentials : userCredentialsFromDb) {
			if (userCredentials.getId().equals(id)) {
				return userCredentials;
			}
		}
		throw new EmptyResultDataAccessException("Product was not found, id = ", id);
	}

	@Override
	public Integer add(UserCredentials userCredentials) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserCredentials> wrapper = (XmlModelWrapper<UserCredentials>) xstream.fromXML(file);
		List<UserCredentials> userCredentialsFromDb = wrapper.getRows();

		for (UserCredentials credentials : userCredentialsFromDb) {
			if (credentials.getEmail().equals(userCredentials.getEmail())) {
				throw new DuplicateKeyException("This email is already exist");
			}
		}

		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		userCredentials.setId(newId);
		userCredentialsFromDb.add(userCredentials);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);

		return newId;
	}

	@Override
	public void update(UserCredentials userCredentials) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserCredentials> wrapper = (XmlModelWrapper<UserCredentials>) xstream.fromXML(file);
		List<UserCredentials> userCredentialsFromDb = wrapper.getRows();

		for (UserCredentials credentials : userCredentialsFromDb) {
			if (credentials.getId().equals(userCredentials.getId())) {
				credentials.setEmail(userCredentials.getEmail());
				credentials.setUserRole(userCredentials.getUserRole());
				break;
			}
		}
		writeNewData(file, wrapper);
	}

	@Override
	public UserCredentials find(String email, String password) {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserCredentials> wrapper = (XmlModelWrapper<UserCredentials>) xstream.fromXML(file);
		List<UserCredentials> userCredentialsFromDb = wrapper.getRows();
		
		for (UserCredentials userCredentials : userCredentialsFromDb) {
			if(userCredentials.getEmail().equals(email) && userCredentials.getPassword().equals(password)){
				return userCredentials;
			}
		}

		return null;
	}

	@Override
	public void delete(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserCredentials> wrapper = (XmlModelWrapper<UserCredentials>) xstream.fromXML(file);
		List<UserCredentials> userCredentialsFromDb = wrapper.getRows();

		UserCredentials found = null;
		for (UserCredentials userCredentials : userCredentialsFromDb) {
			if (userCredentials.getId().equals(id)) {
				found = userCredentials;
				break;
			}
		}

		if (found != null) {
			userCredentialsFromDb.remove(found);
			writeNewData(file, wrapper);
		}

	}

	private File getFile() {
		File file = new File(rootFolder + "user_credentials.xml");
		return file;
	}

	private void writeNewData(File file, @SuppressWarnings("rawtypes") XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
