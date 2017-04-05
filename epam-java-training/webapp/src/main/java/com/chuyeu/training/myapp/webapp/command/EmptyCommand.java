package com.chuyeu.training.myapp.webapp.command;

import javax.servlet.http.HttpServletRequest;

import com.chuyeu.training.myapp.webapp.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}
}
