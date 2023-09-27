package com.winter.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityLogoutHandler implements LogoutSuccessHandler {@Override
	
	/** 인터페이스를 implement 받으면 메서드 오버라이딩 해줘야 함. */
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		log.info("============ Logout Authentication {} =========", authentication);
		
		// 응답으로 리다이랙트 주소를 준다.
		response.sendRedirect("/");
		
	}

}
