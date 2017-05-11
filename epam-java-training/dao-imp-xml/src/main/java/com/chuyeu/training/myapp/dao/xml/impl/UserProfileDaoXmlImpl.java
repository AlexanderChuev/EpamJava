package com.chuyeu.training.myapp.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserProfileDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.xml.impl.comparators.UserAlphabetComparator;
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

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserProfile> wrapper = (XmlModelWrapper<UserProfile>) xstream.fromXML(file);
		List<UserProfile> userProfileFromDb = wrapper.getRows();

		if (commonFilter.getSort().getColumn().toUpperCase().equals("LAST_NAME")) {
			Collections.sort(userProfileFromDb, new UserAlphabetComparator());
		}

		if (commonFilter.getSort().getDirection().toUpperCase().equals("DESC")) {
			Collections.reverse(userProfileFromDb);
		}

		List<UserProfile> userProfileFiltered = new ArrayList<>();
		int from, to;

		if (commonFilter.getPageNumber() == null || commonFilter.getPageNumber().equals(1)
				|| commonFilter.getPageNumber().equals(1)) {
			from = 0;
			to = commonFilter.getLimit();
		} else {
			from = commonFilter.getLimit() * (commonFilter.getPageNumber() - 1);
			to = from + commonFilter.getLimit();
		}

		if (userProfileFromDb.size() < to) {
			to = userProfileFromDb.size();
		}

		for (; from < to; from++) {
			userProfileFiltered.add(userProfileFromDb.get(from));
		}

		return userProfileFiltered;
	}

	@Override
	public UserProfile get(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserProfile> wrapper = (XmlModelWrapper<UserProfile>) xstream.fromXML(file);
		List<UserProfile> userProfileFromDb = wrapper.getRows();
		for (UserProfile userProfile : userProfileFromDb) {
			if (userProfile.getId().equals(id)) {
				return userProfile;
			}
		}
		throw new EmptyResultDataAccessException("User profile was not found, id = ", id);
	}

	@Override
	public Integer insert(UserProfile userProfile) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserProfile> wrapper = (XmlModelWrapper<UserProfile>) xstream.fromXML(file);
		List<UserProfile> userProfileFromDb = wrapper.getRows();

		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		userProfile.setId(newId);
		userProfileFromDb.add(userProfile);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);

		return newId;
	}

	@Override
	public void update(UserProfile userProfile) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserProfile> wrapper = (XmlModelWrapper<UserProfile>) xstream.fromXML(file);
		List<UserProfile> userProfilesFromDb = wrapper.getRows();

		for (UserProfile userProfileFromDb : userProfilesFromDb) {
			if (userProfileFromDb.getId().equals(userProfile.getId())) {
				userProfileFromDb.setFirstName(userProfile.getFirstName());
				userProfileFromDb.setLastName(userProfile.getLastName());
				break;
			}
		}
		writeNewData(file, wrapper);
	}

	@Override
	public void delete(Integer id) {

		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserProfile> wrapper = (XmlModelWrapper<UserProfile>) xstream.fromXML(file);
		List<UserProfile> userProfilesFromDb = wrapper.getRows();

		UserProfile found = null;
		for (UserProfile userProfile : userProfilesFromDb) {
			if (userProfile.getId().equals(id)) {
				found = userProfile;
				break;
			}
		}

		if (found != null) {
			userProfilesFromDb.remove(found);
			writeNewData(file, wrapper);
		}

	}

	@Override
	public Integer getUserProfileQuantity() {
		
		File file = getFile();
		@SuppressWarnings("unchecked")
		XmlModelWrapper<UserProfile> wrapper = (XmlModelWrapper<UserProfile>) xstream.fromXML(file);
		List<UserProfile> userProfilesFromDb = wrapper.getRows();
		return userProfilesFromDb.size();
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
