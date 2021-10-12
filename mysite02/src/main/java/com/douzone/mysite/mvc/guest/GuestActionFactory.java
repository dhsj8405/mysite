package com.douzone.mysite.mvc.guest;


import com.douzone.mysite.mvc.main.MainAction;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.mvc.ActionFactory;

public class GuestActionFactory  extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new ListAction();
		}else if("add".equals(actionName)) {
			action = new AddAction();
		}else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else {
			action = new MainAction();
		}
		return action;
	}
}
