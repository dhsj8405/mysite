package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonResult ex1(@RequestBody GuestbookVo vo) {
//		vo = guestbookService.addMessageBySpa(vo);
		guestbookService.addMessage(vo);
//		vo.setNo(1L);
//		vo.setPassword("");
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult ex2(@RequestParam(value="sn", required=true, defaultValue ="-1")Long no) {
		System.out.println(no);
		List<GuestbookVo> list = guestbookService.findAll(no);
		
//		List<GuestbookVo> list = new ArrayList<>();
//		GuestbookVo vo = new GuestbookVo();
//		vo.setNo(1L);
//		vo.setName("둘리1");
//		vo.setMessage("메시지1");
//		list.add(vo);
//		
//		vo = new GuestbookVo();
//		vo.setNo(2L);
//		vo.setName("둘리2");
//		vo.setMessage("메시지2");
//		list.add(vo);
//		
//		vo = new GuestbookVo();
//		vo.setNo(3L);
//		vo.setName("둘리3");
//		vo.setMessage("메시지3");
//		list.add(vo);
//		
//		vo = new GuestbookVo();
//		vo.setNo(4L);
//		vo.setName("둘리4");
//		vo.setMessage("메시지4");
//		list.add(vo);
//		
//		vo = new GuestbookVo();
//		vo.setNo(5L);
//		vo.setName("둘리5");
//		vo.setMessage("메시지5");
//		list.add(vo);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
	
	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult ex3(@PathVariable("no") Long no, String password) {
		// result = guestbookService.deleteMessage(no,password)를 사용해서 삭제작업
		Long data = 0L;

		System.out.println(no);
		System.out.println(password);
		boolean result = guestbookService.deleteMessage(no,password);
		//1. 삭제가 안된 경우
		if(result != true) {
			data = -1L;
		}
		else {
			data = no;
		}
		//2. 삭제가 된 경우
		
		return JsonResult.success(data);
	}
}
