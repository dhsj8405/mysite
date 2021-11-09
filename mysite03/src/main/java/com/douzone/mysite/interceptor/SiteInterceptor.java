package com.douzone.mysite.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

public class SiteInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ServletContext servletContext;
	@Autowired
	private SiteService siteService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		SiteVo site = (SiteVo)servletContext.getAttribute("siteVo");
		if(site == null) {
			SiteVo siteVo = siteService.getContents();
			servletContext.setAttribute("siteVo", siteVo);
		}

		
		return true;

	}
	
}