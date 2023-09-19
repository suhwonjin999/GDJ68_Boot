package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")  // ROOT 밑에 notice으로 시작하는 모든 url은 해당 컨트롤러에 와서 실행하겠다.
@Slf4j  // Lombok을 사용하기 위한 어노테이션
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	//응답으로 JSON을 보내지 않은 이상, 일반 컨트롤러의 리턴 타입은 ModelAndView, void, String 세 가지이다.
	// 매개변수로 Pager와 Model을 받아서 보냄.
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception{
		
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
//		log.info("getList 실행");
		log.error("getList 실행");
		
		// JSP 파일을 찾으러 가라. 
		return "board/list";
	}
}
