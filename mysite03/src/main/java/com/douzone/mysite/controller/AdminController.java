package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
		
	
//	@RequestMapping({"","/main"})
//	public String main() {
//		return "admin/main";
//	}
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		SiteVo siteVo = siteService.getContents();
		servletContext.setAttribute("siteVo", siteVo);
		model.addAttribute("adminSiteVo", siteVo);
		return "admin/main";
	}
	@RequestMapping(value="/main/upload", method=RequestMethod.POST)
	public String upload(
			SiteVo siteVo,
			@RequestParam(value="file") MultipartFile multipartFile
			) {
		
		siteService.modifyContents(siteVo,multipartFile);
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}

//	
//@RequestMapping(value="/upload", method=RequestMethod.POST)
//public String upload(
//		@RequestParam (value = "comments", required= true, defaultValue = "") String comments,
//		@RequestParam(value="file") MultipartFile multipartFile,
//		Model model) {
//		String url = galleryService.saveImage(multipartFile,comments);
//		model.addAttribute("url",url);
//		return "redirect:/gallery";
//	}
//@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
//public String delete(@PathVariable("no") Long no) {
//	galleryService.deleteImage(no);
//	return "redirect:/gallery";
//}