package com.douzone.mysite.mvc.guest;


import com.douzone.mysite.mvc.main.MainAction;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.mvc.ActionFactory;

public class GuestActionFactory  extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new GuestBookAction();
		}else if("add".equals(actionName)) {
			action = new GuestBookAddAction();
		}else if("deleteform".equals(actionName)) {
			action = new GuestBookDeleteFormAction();
		}else if("delete".equals(actionName)) {
			action = new GuestBookDeleteAction();
		}else {
			action = new MainAction();
		}
		return action;
	}
}
