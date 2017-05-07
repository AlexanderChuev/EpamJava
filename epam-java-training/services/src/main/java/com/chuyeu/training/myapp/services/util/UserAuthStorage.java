package com.chuyeu.training.myapp.services.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chuyeu.training.myapp.datamodel.UserRole;

@Component
@Scope(value = "request")
public class UserAuthStorage {

	private Integer id;
	private UserRole userRole;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
