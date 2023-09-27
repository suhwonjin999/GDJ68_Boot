package com.winter.app.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// MyPage
	@GetMapping("/info")
	public void getInfo() throws Exception{}
	
	
	// 사용자 정보를 세션에서 꺼내올 수 있지만, DB에서 조회하여 꺼내올 수도 있다.
	// 사용자가 로그아웃하지 않은 이상, 수정하더라도 아직 업데이트되기 전 정보가 세션에 남아있기 때문에 수정 전 정보만 세션에 남아있다.
	// 본인수정 페이지
	@GetMapping("update")
	public void setUpdate(HttpSession session, Model model) throws Exception{
		// 로그인 한 상태이니까 사용자 정보를 세션에서 꺼내올 수 있다.
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		// getLogin : id를 조회하여 패스워드가 맞는지 검증 (수정 시 DB에 바로 업데이트되어 수정된 정보가 바로 반영되니까 DB에서 조회하여 가져옴.)
		//memberVO = memberService.getLogin(memberVO);
		
		MemberInfoVO memberInfoVO = new MemberInfoVO();
		memberInfoVO.setName(memberVO.getName());
		memberInfoVO.setBirth(memberVO.getBirth());
		memberInfoVO.setEmail(memberVO.getEmail());
		
		// select한 memberVO를 모델에 담음 
		model.addAttribute("memberVO",memberVO);
		
	}
	
	
	// 정보수정
	@PostMapping("update")
	// 수정된 데이터들은 MemberVO에 담음
	// BindingResult를 매개변수로 집어넣으면, 검증을 다 한 후 메서드에 들어오게 됨.
	// name, password 검증에서 제외하려면 어떻게 해야할까?
	public String setUpdate(@Valid MemberInfoVO memberInfoVO, BindingResult bindingResult) throws Exception{
		
		// Error라는 객체가 누구야? 
		// bindingResult 자체가 Errors 타입이다.
//		bindingResult.addAllErrors(bindingResult);
//		List<ObjectError> ar = bindingResult.getAllErrors();
		// Errors : 인터페이스라 new 생성이 안됨.
		// Errors errors = 
		
		// Errors e = ar.get(0);
		
		Object obj = SecurityContextHolder.getContext().getAuthentication();
		// object 타입을 memberVO 타입으로 형변환시킴
		MemberVO memberVO = (MemberVO) obj;
// memberVO : 실제 데이터를 가리키는 주소가 출력됨.
		memberVO.setEmail("UpdateEmail@naver.com");
		
		List<FieldError> errors = bindingResult.getFieldErrors();
		
		// FieldError 타입인데 errors에서 에러난 필드(멤버변수) 하나씩 꺼내자.
		for(FieldError e:errors) {
			log.info(e.getField());
		}
		
		return "redirect:/";
		
	}
	
//	 로그인 폼
	@GetMapping("login")
	// Spring FORM 태그를 사용한다면, 비어있는 빈 태그를 모델에 담아 보내야 한다.
	// 매개변수 모델을 선언
	public void getLogin(@ModelAttribute MemberVO memberVO) throws Exception{
		
	}
	
/** Spring Security에서 로그인 기능을 대신 해줘서 지워줌.*/	
	// 세션에서 memberVO를 꺼냈을 때 null이면 로그인안됨, null이 아니면 로그인 성공
//	@PostMapping("login")
//	public String getLogin2(MemberVO memberVO, HttpSession session) throws Exception{
//		memberVO = memberService.getLogin(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//			return "redirect:../";
//		}
//		
//		// 현재위치에서 로그인 폼으로 가라.
//		return "redirect:./login";
//		
//	}
	
	// 로그아웃
	@GetMapping("logout")
	public String getLogout(HttpSession session) throws Exception{
		
		// 세션시간을 0으로 초기화하여 세션을 종료되게끔 만듦.
		session.invalidate();
		
		return "redirect:../";
	}
	
	

	//service
//	@GetMapping("join")
//	public String setJoin(Model model) throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("memberVO", memberVO);	
//		return "/member/join";
//	}
	
	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
		// ModelAttribute : 속성명을 모델에 담아라. (리턴되는 값을 모델에 담아라.)
		// 따로 집어넣지 않을 경우, 클래스명의 첫글자를 바꾼것이 모델의 속성명이 된다.
		// 매개변수로 적은 것들은 ModelAttribute 어노테이션을 붙인것과 동일하다. 명시적으로 보여줌.
		// MemberVO가 모델에 담아서 join.jsp까지 옴.
		return;
	}
	
	// 해당 메서드가 실행될 때 MemberVO에서 Vaild 어노테이션을 통해 검증하는 것을 실행한다.
	// 검증하는 어노테이션이 붙인 변수에 대한 검증 결과를 bindingResult 담는다.
	// 에러발생한 경우, 기본적으로 어노테이션이 제공하는 메세지가 출력됨.
	@PostMapping("join")
	// BindingResult 가 구현한 Spring Class가 검증하는 것을 도와줌 (앞에 위치한 매개변수를 검증하겠다. 순서 중요!)
	   public String setjoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile photo)throws Exception{
		
		// 비번이 일치하는지 검증
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		// 검증에 실패한 것이 있다면, 다시 join 폼으로 가라.
		// 에러가 있거나, check가 true 에 해당하면 if문을 실행하여라.
		if(bindingResult.hasErrors() || check) {
			return "member/join";
		}
		
		// 회원가입 진행 => 비번이 암호화되어 INSERT 되어있는지 DB에서 확인!
		int result = memberService.setJoin(memberVO);
		
		
		// 에러가 발생하지 않을 경우, index 메인 페이지로 가라.
	    log.info("photo:{}----size:{}",photo.getOriginalFilename(), photo.getSize());
	    return "redirect:../";
	    
	   }

	
}
