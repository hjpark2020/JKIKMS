package com.jkikms.main.controller;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkikms.main.service.MainService;

@Controller
public class MainController {
	
	@Resource(name = "com.jkikms.main.service.MainService")
	MainService mainService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model md) {
		
		System.out.println();
		Map<String, Object> map = mainService.mainView((String) request.getSession().getAttribute("userId"));
	  
		md.addAttribute("rMap", map);
		for (String key : map.keySet()) { 
			System.out.println(key);
			System.out.println(map.get(key));
			System.out.println("============");
		}
		 
		
		//return "index";
		return "test";
	}
	
	@RequestMapping("/functionA")
	public String functionA() {
		
		return "/functionA/functionA";
	}
}
