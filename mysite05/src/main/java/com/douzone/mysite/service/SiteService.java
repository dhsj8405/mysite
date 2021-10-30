package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {
	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/images";	
	
	@Autowired
	private SiteRepository siteRepository;

	public SiteVo getContents() {
		return siteRepository.findAll();
	}

	public void modifyContents(SiteVo siteVo, MultipartFile multipartFile) {
		String url = null;
		try {
			if( multipartFile.getOriginalFilename().isEmpty() ) {
				siteVo.setProfile(null);
			}
			else {
				String originFilename = multipartFile.getOriginalFilename();
				String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
				String saveFilename = generateSaveFilename(extName);
				if(originFilename == "") {
				}

				byte[] data = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
				os.write(data);
				os.close();
		
				url = URL_BASE + "/" + saveFilename;
				siteVo.setProfile(url);

			}
		} catch (IOException ex) {
			throw new RuntimeException("file upload error:" + ex);
		}
		
		siteRepository.update(siteVo);
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
