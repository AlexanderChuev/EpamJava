package com.chuyeu.training.myapp.dao.xml.impl.comparators;

import java.util.Comparator;

import com.chuyeu.training.myapp.datamodel.UserProfile;

public class UserAlphabetComparator implements Comparator<UserProfile>{

	@Override
	public int compare(UserProfile o1, UserProfile o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}

}
