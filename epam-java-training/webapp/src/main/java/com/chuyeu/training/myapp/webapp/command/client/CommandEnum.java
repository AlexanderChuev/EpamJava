package com.chuyeu.training.myapp.webapp.command.client;

import com.chuyeu.training.myapp.webapp.command.ActionCommand;
import com.chuyeu.training.myapp.webapp.command.LoginCommand;
import com.chuyeu.training.myapp.webapp.command.LogoutCommand;
import com.chuyeu.training.myapp.webapp.command.NewOrderCommand;

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
	},
	
	NEW_ORDER {
		{
			this.command = new NewOrderCommand();
		}
	},
	CURRENT_ORDERS {
		{
			this.command = new LogoutCommand();
		}
	},
	COMPLETED_ORDERS {
		{
			this.command = new LogoutCommand();
		}
	},
	ADD_PRODUCT {
		{
			this.command = new LogoutCommand();
		}
	},
	REMOVE_PRODUCT {
		{
			this.command = new LogoutCommand();
		}
	},
	ADD_ATTRIBUTE {
		{
			this.command = new LogoutCommand();
		}
	};
	
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
	
	
}
