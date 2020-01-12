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
		
		return "index";
	}
	
	@RequestMapping("/functionA")
	public String functionA() {
		
		return "/functionA/functionA";
	}
}
