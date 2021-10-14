package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WirteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("type");
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		//글 수정
//		if(action == "no") {		
//			Long no = Long.parseLong(action);
//			BoardVo boardvo = new BoardDao().findByNo(no);
//			if(authUser == null) {
//				MvcUtil.redirect(request.getContextPath()+"/board", request, response);
//				return;
//			}else if( authUser.getNo() != boardvo.getUserNo()) {
//				MvcUtil.redirect(request.getContextPath()+"/board", request, response);
//				return;
//			}
//			request.setAttribute("boardvo", boardvo);		
//			MvcUtil.forward("board/write", request, response);	
//			return;
//		}
//		if(action =="write") {
//		
//		}else if(action =="update") {
//			Long no = Long.parseLong(request.getParameter("no"));
//		
//		}else if(action =="comment") {
//			
//		}else {
//			
//		}
		//새글		
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
			return;
		}
		
		MvcUtil.forward("board/write", request, response);
		
		//댓글
		
		
	}

}
