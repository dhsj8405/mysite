package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list() {
		int pageIndex = 1;
		return "redirect:/board/list/" + pageIndex;
	}

	@RequestMapping(value = "/list/{pageIndex}", method = RequestMethod.GET)
	public String list(@PathVariable("pageIndex") int pageIndex, Model model) {

		List<BoardVo> list = boardService.find(pageIndex);
		int totalPageNo = boardService.findTotalPage();
		model.addAttribute("curPageNo", pageIndex);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("list", list);
		return "/board/list";
	}

	@RequestMapping(value = "/view/{boardNo}", method = RequestMethod.GET)
	public String view(@PathVariable("boardNo") Long boardNo, Model model, BoardVo boardVo) {
		boardService.updateHit(boardNo);
		boardVo = boardService.findByNo(boardNo);
		model.addAttribute("boardVo", boardVo);
		return "/board/view";

	}

	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write(HttpSession session ) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/board";	
		}else {
			return "/board/write";
		}
	}
	
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String write(HttpSession session, String title, String content, BoardVo vo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(title == null) {
			return "redirect:/board";	
		}
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo(authUser.getNo());
		vo.setOrderNo(0);
		vo.setDept(0);
		boardService.insert(vo);	
		return "redirect:/board"; 		
	}

}
