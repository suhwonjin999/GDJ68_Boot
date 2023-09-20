package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")  // ROOT 밑에 notice으로 시작하는 모든 url은 해당 컨트롤러에 와서 실행하겠다.
@Slf4j  // Lombok을 사용하기 위한 어노테이션
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
/** 모든 메서드에 적용 : 모든 메서드에 포함되어 있다. model.addAttribute("board","notice");
*/	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
/** a 태그 클릭 시 이동하는 메서드 */	
	@GetMapping("fileDown")
	public String getFileDown(FileVO fileVO, Model model) throws Exception{
		
		// view : JSP 경로, model : JSP 에 뿌려중 데이터를 담음 
/** BeanNameViewResolver : VIEW에 적어놓은 이름과 bean(객체)의 이름이 같은 애를 우선으로 찾아 실행하라. 
	return 클래스명과 동일하기 때문에, 클래스에 해당하는 메서드를 찾아 실행함.
	bean의 이름과 동일하지 않을 경우, prifix, sufix 하여 경로를 찾음.
*/		
		fileVO = noticeService.getFileDetail(fileVO);
		model.addAttribute("fileVO", fileVO);
		
/** model fileVO와 board 총 2개가 담김.*/		
		
		return "foleDownView";
		
	}

	//응답으로 JSON을 보내지 않은 이상, 일반 컨트롤러의 리턴 타입은 ModelAndView, void, String 세 가지이다.
	// 매개변수로 Pager와 Model을 받아서 보냄.
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception{
		
		List<BoardVO> ar = noticeService.getList(pager);
		
		// list 호출 시 사용 : list 란 속성명으로 ar 이란 값을 집어넣자.
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
	public String add(NoticeVO noticeVO, MultipartFile [] files) throws Exception{
		// 내가 쓴 글이 파라미터로 제대로 넘어갔는지 보기 위함 (주소가 아니라 멤버변수에 있는 값을 출력하기 위해 ToString 어노테이션을 적어야 함.)
//		log.info("NoticeVO : {}, {}", noticeVO,noticeVO);
		log.info("NoticeVO : {}", noticeVO);
		
//		데이터를 실제 등록하기 위해 서비스에 있는 메서드를 호출한다.
		// add메서드를 호출하면서 매개변수 noticeVO을 넘겨준다.
		int result = noticeService.add(noticeVO, files);
		System.out.println(result);
		
		// 글 쓰고 나면 list 페이지로 이동함
		return "redirect:./list";
	}
	
	
//	@GetMapping("detail")
//	@ResponseBody
//	public BoardVO getDetail(NoticeVO noticeVO) throws Exception{
//		
//		return noticeService.getDetail(noticeVO);
//	}
	
	@GetMapping("detail")
	public String getDetail(NoticeVO noticeVO, Model model) throws Exception{
		
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		model.addAttribute("boardVO",boardVO);
		// jSP 으로 보냄
		
		return "board/detail";
		
	}
	
	
}
