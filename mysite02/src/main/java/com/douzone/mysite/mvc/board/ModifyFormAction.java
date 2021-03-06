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

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo boardvo = new BoardDao().findByNo(no);
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
			return;
		}else if( authUser.getNo() != boardvo.getUserNo()) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
			return;
		}
		
		request.setAttribute("boardvo", boardvo);		
		MvcUtil.forward("board/write", request, response);
	}

}
