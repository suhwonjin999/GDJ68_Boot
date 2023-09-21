package com.winter.app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.notice.NoticeDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestInterceptor implements HandlerInterceptor{

/** URL 기준으로 하여 인터셉터가 실행된다. 어떤 URL이 들어오면 인터셉터가 언제 실행할 것인가. */
	@Autowired
	private NoticeDAO noticeDAO;  // DB를 조회하거나 INSERT 하기위함
	
	@Override
	// return을 true으로 주게되면 컨트롤러에 정상적으로 진입함.
	// return이 false이면 어디로 가야하는지 적어야 함.
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("==== Pre Controller 진입 전 ======");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("==== Post Controller 나가기 전 ======");
	}
	
	@Override
	// JSP가 랜더링한 후 (HTML 파일이 만들어 진 후)
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.info("==== JSP가 만들어 진 후 ======");
	}
	
	
}
