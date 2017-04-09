package com.chuyeu.training.myapp.webapp.command;

import javax.servlet.http.HttpServletRequest;

import com.chuyeu.training.myapp.webapp.resource.ConfigurationManager;

public class NewOrderCommand implements ActionCommand{

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		page = ConfigurationManager.getProperty("path.page.attribute");
		return page;
	}

}
