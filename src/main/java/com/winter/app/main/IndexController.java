package com.winter.app.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** MAIN 페이지 */
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String getIndex() throws Exception{
		return "index";
	}

}
