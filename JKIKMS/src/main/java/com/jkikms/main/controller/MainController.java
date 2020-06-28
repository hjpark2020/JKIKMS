package com.jkikms.main.controller;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.jkikms.main.service.MainService;
import com.jkikms.vo.UserVO;

@Controller
public class MainController {
	
	@Resource(name = "com.jkikms.main.service.MainService")
	MainService mainService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model md) {
		UserVO userVo = (UserVO) request.getSession().getAttribute("userInfo");
		if (userVo != null) {
			System.out.println(userVo.getUserId());
		}
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
		UserVO userInfo = (UserVO) request.getSession().getAttribute("userInfo");
		if(userInfo != null) {
			returnUrl = "login/redirectIndex";
		} else {
			md.addAttribute("result", request.getParameter("result"));
			returnUrl = "login/login";
		}
		
		return returnUrl;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model md) {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		return "/login/redirectIndex";
	}
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model md) {
		String returnUrl = "";
		UserVO userInfo = (UserVO) request.getSession().getAttribute("userInfo");
		if(userInfo != null) {
			returnUrl = "login/redirectIndex";
		} else {
			returnUrl = "login/register";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="/registerResult")
	public String registerResult(HttpServletRequest request, Model md) {
		JSONParser parser = new JSONParser();
		JSONObject jRes = null;
		try {
			jRes = (JSONObject) parser.parse(request.getParameter("jRes"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.addAttribute("jRes", jRes);
		return "login/registerResult";
	}
	
	/*  */
}
