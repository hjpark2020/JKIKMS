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
		md.addAttribute("rMap", mainService.mainView((String) request.getSession().getAttribute("userId")));
		
		return "index";
	}
	
	
	@RequestMapping("/wemeet")
	public String wemeet(HttpServletRequest request, Model md) {
		String returnUrl = "";
		if(request.getSession().getAttribute("userId") != null) {
			md.addAttribute("rMap", mainService.wemeetView((String) request.getSession().getAttribute("userId")));			
		} else {
			returnUrl = "login";
		}
		

		return returnUrl;
	}
	
	@RequestMapping("/smtm")
	public String smtm(HttpServletRequest request, Model md) {
		String returnUrl = "";
		if(request.getSession().getAttribute("userId") != null) {
			md.addAttribute("rMap", mainService.smtmView((String) request.getSession().getAttribute("userId")));			
		} else {
			returnUrl = "login";
		}
		
		return returnUrl;
	}
	
	@RequestMapping("/publicbbs")
	public String publicbbs(HttpServletRequest request, Model md) {
		String returnUrl = "";
		if(request.getSession().getAttribute("userId") != null) {
			md.addAttribute("rMap", mainService.publicbbsView((String) request.getSession().getAttribute("userId")));			
		} else {
			returnUrl = "login";
		}
		
		return returnUrl;
	}
	
	@RequestMapping("/privatebbs")
	public String privatebbs(HttpServletRequest request, Model md) {
		String returnUrl = "";
		if(request.getSession().getAttribute("userId") != null) {
			md.addAttribute("rMap", mainService.privatebbsView((String) request.getSession().getAttribute("userId")));			
		} else {
			returnUrl = "login";
		}
		
		return returnUrl;
	}
	
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, Model md) {
		String returnUrl = "";
		if(request.getSession().getAttribute("userId") != null) {
			md.addAttribute("rMap", mainService.adminView((String) request.getSession().getAttribute("userId")));			
		} else {
			returnUrl = "login";
		}
		
		
		return returnUrl;
	}
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model md) {
		String returnUrl = "";
		if(request.getSession().getAttribute("userId") != null) {
			returnUrl = "index";
		} else {
			returnUrl = "login";
		}
		
		return returnUrl;
	}
}
