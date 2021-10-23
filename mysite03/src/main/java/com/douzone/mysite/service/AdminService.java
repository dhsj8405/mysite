package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.AdminSiteRepository;
import com.douzone.mysite.vo.AdminSiteVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminSiteRepository adminSiteRepository;
	
	public List<AdminSiteVo> getContents() {
		System.out.println("1");
		return adminSiteRepository.findAll();
	}
	
}
