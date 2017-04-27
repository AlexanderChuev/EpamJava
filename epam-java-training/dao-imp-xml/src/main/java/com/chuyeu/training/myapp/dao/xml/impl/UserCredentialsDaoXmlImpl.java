package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCredentials get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer add(UserCredentials userCredentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCredentials update(UserCredentials userCredentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCredentials find(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

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
