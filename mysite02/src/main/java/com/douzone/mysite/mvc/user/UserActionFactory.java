package com.douzone.mysite.mvc.user;

import com.douzone.mysite.mvc.guest.GuestBookAction;
import com.douzone.mysite.mvc.guest.GuestBookAddAction;
import com.douzone.mysite.mvc.guest.GuestBookDeleteAction;
import com.douzone.mysite.mvc.guest.GuestBookDeleteFormAction;
import com.douzone.mysite.mvc.main.MainAction;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.mvc.ActionFactory;

public class UserActionFactory  extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
		}else if("join".equals(actionName)) {
			action = new JoinAction();
		}else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		}else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		}else if("login".equals(actionName)) {
			action = new LoginAction();
		}else if("logout".equals(actionName)) {
			action = new LogoutAction();
		}else {
			action = new MainAction();
		}
		return action;
	}
}
