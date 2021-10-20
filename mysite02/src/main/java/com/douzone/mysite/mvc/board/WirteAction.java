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
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String titleTrim = title.trim();
		if("".equals(titleTrim) ) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
			return;
		}
		String content = request.getParameter("content");
		Long no = null;
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo(authUser.getNo());

		if("comment".equals(type)) {
			no = Long.parseLong(request.getParameter("no"));

			BoardVo parentvo = new BoardDao().findByNo(no);

			vo.setOrderNo(parentvo.getOrderNo()+1);
			vo.setDept(parentvo.getDept()+1);
			vo.setGroupNo(parentvo.getGroupNo());
			
			new BoardDao().updateOrderNo(vo);
			new BoardDao().commentInsert(vo);

		}else if("modify".equals(type)) {
			no = Long.parseLong(request.getParameter("no"));
			vo.setNo(no);

			new BoardDao().update(vo);
		}else if("write".equals(type)){
				
			vo.setOrderNo(0);
			vo.setDept(0);
	
			new BoardDao().insert(vo);			
		}

		MvcUtil.redirect("/mysite02/board?a=list", request, response);	
	}

}
