package com.douzone.mysite.mvc.board;

import com.douzone.mysite.mvc.guestbook.AddAction;
import com.douzone.mysite.mvc.guestbook.DeleteAction;
import com.douzone.mysite.mvc.guestbook.DeleteFormAction;
import com.douzone.mysite.mvc.guestbook.ListAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory{
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("view".equals(actionName)) {
			action = new AddAction();
		}else if("modify".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("write".equals(actionName)) {
			action = new DeleteAction();
		}else {
			action = new listAction();
		}
		return action;
	}
}
