package com.douzone.mysite.mvc.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.util.MvcUtil;

public class GuestBookDeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MvcUtil.forward("user/guestbookdeleteform", request, response);

	}

}
