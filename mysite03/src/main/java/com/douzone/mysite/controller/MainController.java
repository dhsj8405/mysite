package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
public class MainController {
	@Autowired
	private SiteService siteService;
//	@Auth("USER")
//	@Auth(role = "USER")
	@RequestMapping({"","/main"})
	public String index(Model model) {
		SiteVo siteVo = siteService.getContents();
		model.addAttribute("siteVo", siteVo);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	@ResponseBody
	@RequestMapping("/msg02")
	public Map<String, Object> message02(/*HttpServletResponse resp*/) throws Exception{
//		resp.setContentType("application/json; charset=UTF-8");
//		resp.getWriter().print("{\"message\":\"Hello World\"}");
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		return map;
	}
}
