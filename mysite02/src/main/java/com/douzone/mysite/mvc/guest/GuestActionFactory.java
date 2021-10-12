package com.douzone.mysite.mvc.guest;


import com.douzone.mysite.mvc.main.MainAction;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.mvc.ActionFactory;

public class GuestActionFactory  extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("guestbook".equals(actionName)) {
			action = new GuestBookAction();
		}else if("guestbookadd".equals(actionName)) {
			action = new GuestBookAddAction();
		}else if("guestbookdeleteform".equals(actionName)) {
			action = new GuestBookDeleteFormAction();
		}else if("guestbookdelete".equals(actionName)) {
			action = new GuestBookDeleteAction();
		}else {
			action = new MainAction();
		}
		return action;
	}
}
