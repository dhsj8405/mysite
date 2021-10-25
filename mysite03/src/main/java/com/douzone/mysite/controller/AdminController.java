package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.AdminSiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
//	@RequestMapping({"","/main"})
//	public String main() {
//		return "admin/main";
//	}
	@RequestMapping({"","/main"})
	public String main(Model model) {
		AdminSiteVo adminSiteVo = adminService.getContents();
		model.addAttribute("adminSiteVo", adminSiteVo);
		return "admin/main";
	}
	@RequestMapping(value="/main/upload", method=RequestMethod.POST)
	public String upload(
			AdminSiteVo adminSiteVo,
//			@RequestParam (value = "title", required= true, defaultValue = "") String title,
//			@RequestParam (value = "welcom", required= true, defaultValue = "") String welcome,
			@RequestParam(value="file") MultipartFile multipartFile
			) {
		System.out.println("#####################");
		System.out.println(adminSiteVo);
		System.out.println("#####################");
		if(multipartFile.isEmpty()) {
			return "redirect:/admin/main";
		}
		adminService.modifyContents(adminSiteVo,multipartFile);

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