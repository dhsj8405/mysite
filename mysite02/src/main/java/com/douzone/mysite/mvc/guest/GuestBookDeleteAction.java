package com.douzone.mysite.mvc.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;

import comd.douzone.web.mvc.Action;
import comd.douzone.web.util.MvcUtil;

public class GuestBookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long deleteNo = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		new GuestbookDao().delete(deleteNo, password);
		//리다이렉트 응답
		MvcUtil.redirect("/mysite02/user?a=guestbook", request, response);
	}

}
