package com.douzone.mysite.mvc.main;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
