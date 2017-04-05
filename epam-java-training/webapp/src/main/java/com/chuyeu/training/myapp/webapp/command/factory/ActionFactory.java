package com.chuyeu.training.myapp.webapp.command.factory;

import javax.servlet.http.HttpServletRequest;

import com.chuyeu.training.myapp.webapp.command.ActionCommand;
import com.chuyeu.training.myapp.webapp.command.EmptyCommand;
import com.chuyeu.training.myapp.webapp.command.client.CommandEnum;
import com.chuyeu.training.myapp.webapp.resource.MessageManager;

public class ActionFactory {

	public ActionCommand defineCommand(HttpServletRequest request) {
		
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		
		if (action == null || action.isEmpty()) {
		return current;
		}
		
		try {
		CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
		current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
		request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
		}
}
