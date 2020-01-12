package com.jkikms.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/loginCheck")
	public String login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		session.setAttribute("userId", request.getParameter("test"));

		
		model.addAttribute("id", request.getParameter("test"));
		
		return "test";
	}
}
