package com.jkikms.functionA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
	
	/*
	 * @RequestMapping("/test") public String test() { return "Boot Test"; }
	 */

	@RequestMapping("/")
	public String index() {
		
		// 세션 체크
		// 로고
		// 상단메뉴
		// 메인화면 구성
		// footer

				
		return "index";
	}
	
	@RequestMapping("/functionA")
	public String functionA() {
		
		return "/functionA/functionA";
	}
}
