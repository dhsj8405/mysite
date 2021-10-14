package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory{
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		 if("writeform".equals(actionName)) {
			action = new WirteformAction();
		}else if("write".equals(actionName)) {
			action = new WirteAction();
		}else if("view".equals(actionName)) {
			action = new viewAction();
		}else if("modifyform".equals(actionName)) {
			action = new modifyformAction();
		}else if("modify".equals(actionName)) {
			action = new modifyAction();
		}else {
			action = new ListAction();
		}
		return action;
	}
}
