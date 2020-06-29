package com.jkikms.main.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.jkikms.common.util.StartConfig;
import com.jkikms.main.service.MainService;
import com.jkikms.vo.MenuVO;
import com.jkikms.vo.StaticVO;
import com.jkikms.vo.UserVO;

@Controller
public class MainController {
	
	@Resource(name = "com.jkikms.main.service.MainService")
	MainService mainService;
	
	@RequestMapping(value= {"/"})
	public String index(HttpServletRequest request, Model md) {
		md.addAttribute("menuList", StaticVO.getMenuList());
		md.addAttribute("currentMenu", "");
		return "index";
	}
	
	
	@RequestMapping(value= {"/wemeet","/smtm","/publicbbs","/privatebbs"})
	public String wemeet(HttpServletRequest request, HttpServletResponse response, Model md) {
		md.addAttribute("menuList", StaticVO.getMenuList());
		String returnUrl = "";
		
		String currentMenu = request.getRequestURI().replace("/", "");
		if(request.getSession().getAttribute("userInfo") != null) {
			UserVO userVo = (UserVO) request.getSession().getAttribute("userInfo");
			MenuVO menuVo = mainService.nowMenu(currentMenu);

			if(userVo.getLevel() <= menuVo.getLevel()) {
				md.addAttribute("currentMenu", currentMenu);				
				returnUrl = menuVo.getMenuPath();
			} else {
				returnUrl = "authorityFail";
			}
					
		} else {
			md.addAttribute("beforeMenu", currentMenu);
			returnUrl = "login/redirectLogin";
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
			md.addAttribute("menuList", StaticVO.getMenuList());
			md.addAttribute("beforeMenu", request.getParameter("beforeMenu"));
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
			md.addAttribute("menuList", StaticVO.getMenuList());
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
	
	@RequestMapping(value="/menuReset")
	public String menuReset(HttpServletRequest request, Model md) {
		StaticVO.setMenuList(mainService.selectMenu());
		
		return "/";
	}
}
