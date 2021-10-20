package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		
		int pageno = 1;
		String keyword = null;
		int totalPageNo;
		List<BoardVo> list;
		BoardDao dao = new BoardDao();

		if(request.getParameter("pageindex") != null) {
			String pageindex = request.getParameter("pageindex");
			pageno = Integer.parseInt(pageindex);
		}
				
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			Long no = authUser.getNo();
			request.setAttribute("userNo", no);
		}

		if(request.getParameter("kwd") ==null) {
			list = dao.findAll(pageno);
			totalPageNo = dao.findTotalPage();
			
		}else {
			keyword = request.getParameter("kwd");
			list = dao.findAll(pageno,keyword);
			totalPageNo = dao.findTotalPage(keyword);
			request.setAttribute("keyword", keyword);
		}
	
		request.setAttribute("curPageNo", pageno);
		request.setAttribute("totalPageNo", totalPageNo);
		request.setAttribute("list", list);

		MvcUtil.forward("board/list", request, response);
	}

}
