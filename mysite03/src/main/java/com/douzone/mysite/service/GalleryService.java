package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GalleryService {
	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/images";					//위에거 매핑한것
	
	public String restore(MultipartFile multipartFile) {
		String url = null;
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}

			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
			String saveFilename = generateSaveFilename(extName);
			
			System.out.println("############" + originFilename);
			System.out.println("############" + saveFilename);
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + saveFilename;
			}catch(IOException ex) {
				throw new RuntimeException("file upload error:" + ex);
			}
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename  += calendar.get(Calendar.YEAR);
		filename  += calendar.get(Calendar.MONTH);
		filename  += calendar.get(Calendar.DATE);
		filename  += calendar.get(Calendar.HOUR);
		filename  += calendar.get(Calendar.MINUTE);
		filename  += calendar.get(Calendar.SECOND);
		filename  += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		return filename ;
	}
	
}
