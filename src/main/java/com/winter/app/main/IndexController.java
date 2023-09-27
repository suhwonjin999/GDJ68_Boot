package com.winter.app.main;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.winter.app.member.MemberVO;

/** MAIN 페이지 */
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String getIndex() throws Exception{
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin(@ModelAttribute MemberVO memberVO) throws Exception{
//		SecurityContext context = 
		return "index";
	}

}
