package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WirteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		System.out.println(authUser.getNo());

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo(authUser.getNo());
		vo.setGroupNo(groupNo+1);
		vo.setOrderNo(0);
		vo.setDept(0);
		
		new BoardDao().insert(vo);
		MvcUtil.redirect("/mysite02/board?a=list", request, response);	}

}
