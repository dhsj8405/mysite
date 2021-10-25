package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.AdminSiteRepository;
import com.douzone.mysite.vo.AdminSiteVo;

@Service
public class AdminService {
	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/images";	
	
	@Autowired
	private AdminSiteRepository adminSiteRepository;

	public AdminSiteVo getContents() {
		return adminSiteRepository.findAll();
	}

	public void modifyContents(AdminSiteVo adminSiteVo, MultipartFile multipartFile) {
		String url = null;
		try {
		String originFilename = multipartFile.getOriginalFilename();
		String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
		String saveFilename = generateSaveFilename(extName);
		System.out.println("############" + originFilename);
		System.out.println("############" + saveFilename);
			
		byte[] data = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
		os.write(data);
		os.close();

		url = URL_BASE + "/" + saveFilename;
		adminSiteVo.setProfile(url);
		
		System.out.println("#####################");
		System.out.println(adminSiteVo);
		System.out.println("#####################");
		adminSiteRepository.update(adminSiteVo);
		
		} catch (IOException ex) {
			throw new RuntimeException("file upload error:" + ex);
		}

	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		return filename;
	}

}
