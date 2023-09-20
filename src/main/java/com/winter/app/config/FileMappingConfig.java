package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebMvcConfigurer 라는 인터페이스를 구현함 : implements 클래스명
// DIOC : bean 등록
//***-context.xml 과 동일한 역할을 한다. 
@Configuration  
public class FileMappingConfig implements WebMvcConfigurer {
	
/** 요청 URL 주소가 들어오면 파일경로를 찾으세요*/
	// Local file 위치
	@Value("${app.upload.mapping}")
	private String filePath;
	
	// 요청 URL 경로
	@Value("${app.url.path}")
	private String urlPath;
	
/** 메서드 오버라이딩
 *  Registry 에다 등록하세요.
*/	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// 요청 URL을 등록함.
		registry.addResourceHandler(urlPath)
		
		// Local File 위치 
				.addResourceLocations(filePath);
		
	}
	

}
