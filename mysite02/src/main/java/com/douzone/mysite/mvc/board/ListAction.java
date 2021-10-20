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
		
		if(request.getParameter("kwd") !=null) {
			String keyword = request.getParameter("kwd");
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.search(keyword);
			request.setAttribute("list", list);

			MvcUtil.forward("board/list", request, response);
			return;
		}
		
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			Long no = authUser.getNo();
			request.setAttribute("userNo", no);
		}
		
		int pageno = 1;
	
		if(request.getParameter("pageindex") != null) {
			String pageindex = request.getParameter("pageindex");
			pageno = Integer.parseInt(pageindex);
		}
		
//		int leftEdgeNo = (pageno-1)/5 *5 +1;
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.findAll(pageno);
		int totalPageNo = dao.findTotalPage();
		
		request.setAttribute("curPageNo", pageno);
//		request.setAttribute("leftEdgeNo", leftEdgeNo);
		request.setAttribute("totalPageNo", totalPageNo);
		request.setAttribute("list", list);

		MvcUtil.forward("board/list", request, response);
	}

}
