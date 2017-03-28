package com.chuyeu.training.myapp.datamodel;

public class UserCredentials extends AbstractModel {

	private static final long serialVersionUID = -5775496814278275460L;

	private String email;
	private String password;
	private UserRole userRole;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserCredentials [email=" + email + ", password=" + password + ", userRole=" + userRole + "]";
	}

}
