package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
	
	

	// <bean class=""> bean 등록
	@Bean
	public LocaleResolver localeResolver() {
		
		// 1. session을 이용하는 방식
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		// 2. Cookie를 이용하는 방식
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
		// 쿠키에 lang 라는 값으로 영어를 볼 것인지, 한국어를 볼 것인가 담는다.
		cookieLocaleResolver.setCookieName("lang");
		
		// return resolver;  // session을 이용할 경우 리턴
		return cookieLocaleResolver;  // cookie를 이용할 경우 리턴 
	}
	
	// 어떤 언어를 쓸 것인지 인터셉터에서 가로채서 정한다.
	// Message Interceptor 객체 생성 (클래스가 이미 존재함.)
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		
		// 들어온 파라미터 값을 보고 인터셉터가 어떤 언어를 쓸 것인가를 결정해준다.
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		
		interceptor.setParamName("lang");
		// parameter를 받아서 언어를 구분함
		// url?lang=en  영어로 보겠다.
		// url?lang=ko  한국어로 보겠다.
		
		return interceptor;
	}
	
}
