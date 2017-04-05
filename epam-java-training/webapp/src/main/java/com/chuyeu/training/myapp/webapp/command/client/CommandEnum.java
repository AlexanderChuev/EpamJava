package com.chuyeu.training.myapp.webapp.command.client;

import com.chuyeu.training.myapp.webapp.command.ActionCommand;
import com.chuyeu.training.myapp.webapp.command.LoginCommand;
import com.chuyeu.training.myapp.webapp.command.LogoutCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	};
	
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
