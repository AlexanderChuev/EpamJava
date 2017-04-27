package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserProfileDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.xml.impl.wrapper.XmlModelWrapper;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class UserProfileDaoXmlImpl implements IUserProfileDao {

	private final XStream xstream = new XStream(new DomDriver());
	
	@Value("${root.folder}")
	private String rootFolder;
	
	@Override
	public List<UserProfile> getAll(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile insert(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile update(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getUserProfileQuantity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private File getFile() {
		File file = new File(rootFolder + "user_profiles.xml");
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
