package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		Long no = Long.parseLong(request.getParameter("no"));
		new BoardDao().updateHit(no);
		BoardVo boardvo = new BoardDao().findByNo(no);
		request.setAttribute("boardvo", boardvo);
		MvcUtil.forward("board/view", request, response);
	}

}
