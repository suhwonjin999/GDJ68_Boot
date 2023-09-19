package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// FORM으로 이동 
	@GetMapping("add")
	public String add() throws Exception{
		return "board/add";
	}
	
	// DB에 INSERT
	// NiticeVO가 BoardVO 타입이기 때문에 받아와도 상관없다.
	@PostMapping("add")
	public String add(NoticeVO noticeVO) throws Exception{
		// 내가 쓴 글이 파라미터로 제대로 넘어갔는지 보기 위함 (주소가 아니라 멤버변수에 있는 값을 출력하기 위해 ToString 어노테이션을 적어야 함.)
//		log.info("NoticeVO : {}, {}", noticeVO,noticeVO);
		log.info("NoticeVO : {}", noticeVO);
		
//		데이터를 실제 등록하기 위해 서비스에 있는 메서드를 호출한다.
		// add메서드를 호출하면서 매개변수 noticeVO을 넘겨준다.
		int result = noticeService.add(noticeVO);
		System.out.println(result);
		
		// 글 쓰고 나면 list 페이지로 이동함
		return "redirect:./list";
	}
	
	
}
