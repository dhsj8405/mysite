package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;


@RestController("userApiController") //핸들러들 전부 responsebody 가진것처럼 메세지 컨버터 작용 : Auth와 비슷
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	//@ResponseBody
	//@RequestMapping("/checkemail")
	@GetMapping("/checkemail")
	public JsonResult checkemail(
			@RequestParam(value="email", required = true, defaultValue ="") String email) {
		
		UserVo userVo = userService.getUser(email);
		
		return JsonResult.success(userVo != null);
		
//		Map<String, Object> map = new HashMap<>();
//		map.put("result", "seuccess");
//		map.put("data", userVo != null);
//		map.put("message",null);
//		return map;
	}
}
