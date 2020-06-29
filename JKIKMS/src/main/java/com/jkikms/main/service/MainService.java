package com.jkikms.main.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.jkikms.vo.MenuVO;

public interface MainService {

	Map<String, Object> mainView(String userId);
	
	Map<String, Object> wemeetView(String userId);
	
	Map<String, Object> smtmView(String userId);
	
	Map<String, Object> publicbbsView(String userId);
	
	Map<String, Object> privatebbsView(String userId);
	
	Map<String, Object> adminView(String userId);
	
	
	List<MenuVO> selectMenu();
	MenuVO nowMenu(String currentMenu);

}
