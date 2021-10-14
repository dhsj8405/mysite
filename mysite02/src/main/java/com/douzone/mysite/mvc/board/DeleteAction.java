package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
//		BoardVo tmpvo = new BoardDao().findByNo(no);
		BoardVo vo = new BoardVo();
//		tmpvo.setNo(vo.getNo());
//		tmpvo.setGroupNo(vo.getGroupNo());
//		tmpvo.setOrderNo(vo.getOrderNo());
//		tmpvo.setDept(vo.getDept());
		
		new BoardDao().delete(no);
//		new BoardDao().informInsert(tmpvo);
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	
	}

}
