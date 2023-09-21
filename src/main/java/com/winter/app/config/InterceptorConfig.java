package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptors.TestInterceptor;

// 톰켓이 처음 부팅될 때 프로그램이 실행하기 전 설정 파일들을 먼저 읽을 수 있도록 함
@Configuration // 스프링 부트가 처음 실행될 때 처음 설정부분을 먼저 읽어라.
public class InterceptorConfig implements WebMvcConfigurer {
	
	// 만들어진 객체 가져오기
	@Autowired
	private TestInterceptor testInterceptor;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	// 어느 URL이 왔을 때 어떤 인터셉터를 쓸 것인가 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 등록 
		// ROOT 밑에 notice/list가 오면 해당 인터셉터를 거쳐가자. 
		registry.addInterceptor(testInterceptor)
				.addPathPatterns("/notice/list");
		
		// ROOT로 시작하는 모든 경로는 localeChangeInterceptor 를 거쳐가자.
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**")
				;
		
	}
	
	

}
