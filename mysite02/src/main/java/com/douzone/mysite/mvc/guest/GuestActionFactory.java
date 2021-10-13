package com.douzone.mysite.mvc.guest;


import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestActionFactory  extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("add".equals(actionName)) {
			action = new AddAction();
		}else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else {
			action = new ListAction();
		}
		return action;
	}
}
