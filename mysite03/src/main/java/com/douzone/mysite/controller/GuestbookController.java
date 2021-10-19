package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value= "",method=RequestMethod.GET)
		public String list(GuestbookVo guestbookVo, Model model) {
		List<GuestbookVo> list = guestbookService.find(guestbookVo);
		model.addAttribute("list",list);
		return "guestbook/list";
		}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}",  method=RequestMethod.GET)
	public String delete(@PathVariable ("no") Long no, Model model) {
		model.addAttribute("no",no);
		return "guestbook/delete";
	}


	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(Long no, String password) {
		guestbookService.delete(no, password);
		return "redirect:/guestbook";
	}

}
