package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.util.MvcUtil;

public class CheckAuth {
	public void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
			return;
		}
	}
	public void checkBoardOwnership(HttpServletRequest request, HttpServletResponse response, BoardVo boardvo)throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if( authUser.getNo() != boardvo.getUserNo()) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
			return;
		}
	}
}
