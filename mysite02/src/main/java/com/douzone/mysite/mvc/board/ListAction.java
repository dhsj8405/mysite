package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		
//		GuestbookDao dao = new GuestbookDao();	
//		List<GuestbookVo> list = dao.findAll();
//		
//		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);
	}

}