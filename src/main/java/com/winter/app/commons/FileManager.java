package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	//file 저장 후 파일명 리턴
		public String save(String path, MultipartFile multipartFile)throws Exception{
			//어디에?, 어떤파일을?
			//1. 파일 객체 생성
			File file = new File(path);
			
			// 파일 위치에 폴더가 없으면 새로운 폴더 만들어라.
			if(!file.exists()) {
				file.mkdirs();
			}
			
			//2. 저장할 파일명 생성
			// getOriginalFilename : 로컬컴퓨터에 저장되어 있는 사진명
			String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
			
			//3. 파일을 저장
			file = new File(file, fileName);
			//FileCopyUtils.copy
			//FileCopyUtils.copy(multipartFile.getBytes(), file);
			//transferTo
			multipartFile.transferTo(file);
			
			return fileName;
		}
}
