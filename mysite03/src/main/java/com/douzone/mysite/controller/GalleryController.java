package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index() {
		
		return "gallery/index";
	}
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
			@RequestParam (value = "comments", required= true, defaultValue = "") String comments,
			@RequestParam(value="file") MultipartFile multipartFile,
			Model model) {
			System.out.println("comments:"+comments);
			String url = galleryService.restore(multipartFile);
			System.out.println(url);
			model.addAttribute("url",url);
			return "redirect:/gallery";
		}
//	List<GuestbookVo> list = guestbookService.getMessageList();
//	model.addAttribute("list", list);
}
